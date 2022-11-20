package shop.kokodo.calculateservice.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import shop.kokodo.calculateservice.dto.CommissionPolicyDto;
import shop.kokodo.calculateservice.dto.CostAndCommissionDto;
import shop.kokodo.calculateservice.entity.Order;
import shop.kokodo.calculateservice.messagequeue.OrderKafkaProducer;
import shop.kokodo.calculateservice.partition.OrderIdRangePartitioner;
import shop.kokodo.calculateservice.repository.order.OrderRepository;
import shop.kokodo.calculateservice.service.CalculateService;
import shop.kokodo.calculateservice.service.ProductService;
import shop.kokodo.calculateservice.service.SellerService;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : shop.kokodo.calculateservice.job
 * fileName       : CalculateStepConfiguration
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */

/**
 * 변경 사항
 * 1.AsynceItemProcessor를 사용할 것인가?
 * 2.따로 정의한 Partioner를 사용할 것인가 말것인가
 * 3.주문 데이터가 없을 경우 Partioning 예외처리 진행해줘야함
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class CalculateStepConfiguration {

    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private final OrderRepository orderRepository;
    //TODO:(1)통합 테스트시 주석해제
    private final ProductService productService;
    //TODO:(1)통합 테스트시 주석해제
    private final SellerService sellerService;
    private final CalculateService calculateService;
    private final OrderKafkaProducer orderKafkaProducer;
    private int chunkSize;

    @Value("${chunkSize:10}")
    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    private int poolSize;

    @Value("${poolSize:10}")
    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    @Bean
    public TaskExecutor calculateTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 몇 개의 Thread로 관리할 것인지에 대한 명시 정보
        taskExecutor.setCorePoolSize(poolSize);
        // 작업 진행시 처리되지 않을 작업이 존재할 경우 몇 개의 Thread로 작업을 처리할 지에 대한 명시
        taskExecutor.setMaxPoolSize(poolSize);
        // Thread별 이름 확인을 위해 "calculate-thread-" 고정
        taskExecutor.setThreadNamePrefix("multi-thread-");
        // 종료시 작업완료까지 대기를 할 지에 대한 설정
        taskExecutor.setWaitForTasksToCompleteOnShutdown(Boolean.TRUE);
        // 실행 초기화
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    public Step apiMasterStep() throws Exception {
        return stepBuilderFactory.get("apiMasterStep")
                .partitioner(calculateSlaveStep().getName(), partitioner(null, null))
                .step(calculateSlaveStep())
                .partitionHandler(partitionHandler())
//                몇 개의 파티션으로 나눌지에 대한 설정, partionHandler에서 지정함
//                .gridSize(3)
//                .taskExecutor(calculateTaskExecutor())
                .build();
    }

    @Bean
    public Step calculateSlaveStep() throws Exception {
        return stepBuilderFactory.get("calculateSlaveStep")
                .<Order, Order>chunk(chunkSize)
                .reader(calculateReader(null, null))
//                .processor(calculateProcessor())
                .writer(calculateWriter(null, null))
                //생성한 스레드중에서 실제로 몇 개를 사용할 것인지를 명시
//                .throttleLimit(poolSize)
                .build();
    }

    @Bean
    public TaskExecutorPartitionHandler partitionHandler() throws Exception {
        // 로컬 환경에서 멀티쓰레드로 수행할 수 있도록 TaskExecutorPationHandler 구현체를 사용, Spring Batch에서 지원한다.
        TaskExecutorPartitionHandler partitionHandler = new TaskExecutorPartitionHandler();
        // Worker로 실행할 Step을 지정합니다.
        partitionHandler.setStep(calculateSlaveStep());
        // 멀티쓰레드로 실행하기 위해 TaskExecutor 를 지정합니다.
        partitionHandler.setTaskExecutor(calculateTaskExecutor());
        //몇 개의 파티션으로 나눌지에 대한 설정
        //쓰레드 개수와 gridSize를 맞추기 위해서 poolSize를 gridSize로 등록합니다
        partitionHandler.setGridSize(poolSize);
        return partitionHandler;
    }

    //JdbcPaging, JpaPaging Reader를 사용하지 않을 경우 동시성 문제가 발생한다.
    @Bean
    @StepScope
    public JpaPagingItemReader<Order> calculateReader(
            @Value("#{stepExecutionContext[minId]}") Long minId,
            @Value("#{stepExecutionContext[maxId]}") Long maxId) {

        //개발 이후 시간값으로 정산이 필요할 경우 사용할 수 있또록 변수값 추가
        Map<String, Object> params = new HashMap<>();
        params.put("minId", minId);
        params.put("maxId", maxId);
//        params.put("createDate", LocalDate.parse(createDate, DateTimeFormatter.ofPattern("yyyy--MM--dd")));

        log.info("reader minId={}, maxId={}", minId, maxId);

        return new JpaPagingItemReaderBuilder<Order>()
                .name("calculateReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                //****************************error 지정 예지점 ************************
                .queryString("select o " +
                        "from Order o " +
                        "where o.orderStatus='ORDER_SUCCESS' and o.id between :minId and :maxId")
                .parameterValues(params)
                //기본 값은 실패 지점에서 다시 시작하도록 설정되어있다. 하지만 멀티스레드 환경에서는 매우 위험한 방법이므로 설정을 false 값으로 설정한다.
                .saveState(false)
                .build();
    }

    @Bean
    @StepScope
    public ItemWriter<Order> calculateWriter(
            @Value("#{stepExecutionContext[minId]}") Long minId,
            @Value("#{stepExecutionContext[maxId]}") Long maxId) {
        return items -> {
            System.out.println("===writer start===");
            for (Order o : items) {
                log.info("===process 1===");
                //TODO: (1)통합 테스트 진행시 주석 해제
                List<Long> sellerId = productService.getSellerId(o.getId());
                log.info("===process 2===");
                //TODO: (1)통합 테스트시 주석해제
//              sellerId와 commissionPolicy의 idx 관계는 절대적으로 지켜져야한다 예를 들어 sellerId(0)번째의 수수료는 commissionPolicy(0)번째이다. sellerId(0)일 경우 commissionPolicy(1)이면 안된다.
                List<CommissionPolicyDto> commissionPolicy = sellerService.searchCommissionPolicy(sellerId);
                log.info("===process 3===");
                CostAndCommissionDto costAndCommissionDto = calculateService.getCommission(commissionPolicy, o.getTotalPrice());
                log.info("===process 4===");
                calculateService.makeCalculate(costAndCommissionDto.getCost(), costAndCommissionDto.getCommissionList());
                log.info("===process 5===");
//                //TODO: (1)통합 테스트 진행시 주석 해제, 주문의 상태를 정산완료로 수정
                orderKafkaProducer.sendOrderStatus("order-id-topic", o.getId());
                log.info("===process 6===");
            }
            log.info("===writer end===");
        };
    }

    @Bean
    @StepScope
    public OrderIdRangePartitioner partitioner(
            @Value("#{jobParameters['startDate']}") String startDate,
            @Value("#{jobParameters['endDate']}") String endDate) {
        log.info("startdate {} enddate {}", startDate, endDate);
        LocalDateTime startLocalDate = LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endLocalDate = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return new OrderIdRangePartitioner(orderRepository, startLocalDate, endLocalDate);
    }
}