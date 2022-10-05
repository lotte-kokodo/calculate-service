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
import shop.kokodo.calculateservice.client.ProductServiceClient;
import shop.kokodo.calculateservice.entity.Calculate;
import shop.kokodo.calculateservice.entity.Commission;
import shop.kokodo.calculateservice.entity.Order;
import shop.kokodo.calculateservice.partition.OrderIdRangePartitioner;
import shop.kokodo.calculateservice.repository.interfaces.OrderRepository;
import shop.kokodo.calculateservice.service.CalculateService;
import shop.kokodo.calculateservice.service.CommissionService;
import shop.kokodo.calculateservice.service.ProductService;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
 * 2-1.Spring Batch에서 제공하는 interface를 사용자 정의하여 나만의 Partioner를 구축하고 싶었으나 역량 부족으로 실패
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class CalculateStepConfiguration {

    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final OrderRepository orderRepository;
    private final CommissionService commissionService;
    private final ProductService productService;
    private final CalculateService calculateService;

    private int chunkSize;

    @Value("${chunkSize:100}")
    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    private int poolSize;

    @Value("${poolSize:10}")
    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    @Bean
    public TaskExecutor calculateTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 몇 개의 Thread로 관리할 것인지에 대한 명시 정보
        taskExecutor.setCorePoolSize(poolSize);
        // 작업 진행시 처리되지 않을 작업이 존재할 경우 몇 개의 Thread로 작업을 처리할 지에 대한 명시
        taskExecutor.setMaxPoolSize(poolSize);
        // Thread별 일므 확인을 위해 "calculate-thread-" 고정
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
            @Value("#{stepExecutionContext[maxId]}") Long maxId){

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

//    private ItemProcessor<Order, Order> calculateProcessor() {
//    }


    @Bean
    @StepScope
    public ItemWriter<Order> calculateWriter(
            @Value("#{stepExecutionContext[minId]}") Long minId,
            @Value("#{stepExecutionContext[maxId]}") Long maxId) {

        return items -> {
            System.out.println("====================== writer start============================");
            for (Order o : items) {
                Long sellerId = productService.getSellerId(o.getId());
                Commission commission = commissionService.findCommission(sellerId);
                Calculate calculate = Calculate.createCalculate(commission, calculateService.getFinalPaymentCost(commission, o.getTotalPrice()));
                calculateService.saveCalculate(calculate);
            }
            System.out.println("====================== writer end============================");
//            productBackupRepository.saveAll(items);
        };
    }

    @Bean
    @StepScope
    public OrderIdRangePartitioner partitioner(
            @Value("#{jobParameters['startDate']}") String startDate,
            @Value("#{jobParameters['endDate']}") String endDate) {
        log.info("startdate {} enddate {}",startDate, endDate);
        LocalDateTime startLocalDate = LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endLocalDate = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return new OrderIdRangePartitioner(orderRepository, startLocalDate, endLocalDate);
    }

//    @Bean
//    @StepScope
//    public JpaItemWriter<Order> calculateLogWriter(){
//        return items -> {
//            System.out.println("============================== try : " + tryNum + "==============================");
//            for (Order item : items) {
//                System.out.println(item.getOrderStatus());
//                tryNum++;
//            }
//            System.out.println("============================================================================");
//
//        };
//    }

//    @Bean
//    @StepScope
//    public ItemReader<OrderDto> calculateReader(@Value("#{stepExecutionContext['order']}") OrderDto orderDto) throws Exception {
//
//        JdbcPagingItemReader<OrderDto> reader = new JdbcPagingItemReader<>();
//
//        reader.setDataSource(dataSource);
//        reader.setPageSize(chunkSize);
//        reader.setRowMapper(new BeanPropertyRowMapper<>(OrderDto.class));
//
//        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
//        queryProvider.setSelectClause("order_id, user_id, total_price");
//        queryProvider.setFromClause("from orders");
//        queryProvider.setWhereClause("where order_status = :order_id");
//
//        Map<String, Order> sortKeys = new HashMap<>(1);
//        sortKeys.put("order_id", Order.DESCENDING);
//        queryProvider.setSortKeys(sortKeys);
//
//        //여기서 설정한 Parameter 값이 84번 줄의 type에 매핑된다.
//        reader.setParameterValues(QueryGenerator.getParameterForQuery("orderStatus", String.valueOf(orderDto.getId())));
//        reader.setQueryProvider(queryProvider);
//        reader.afterPropertiesSet();
//
//        return reader;
//    }

//    @Bean
//    public ItemProcessor calculateProcessor(){
//        ClassifierCompositeItemProcessor<OrderDto, ApiRequestVO> processor = new ClassifierCompositeItemProcessor<>();
//        //ProcessorClassifier는 Key는 Classifer, value는 반환할 T이다. 객체에 맞게 프로세서슬 반환해주려고 하는것이 목적이다.
//
//        ProcessorClassifier<OrderDto, ItemProcessor<?, ? extends ApiRequestVO>> classifier = new ProcessorClassifier();
//        Map<String, ItemProcessor<OrderDto, ApiRequestVO>> processorMap = new HashMap<>();
//        processorMap.put("500", new OrderProcessor());
//
//        classifier.setProcessorMap(processorMap);
//
//        processor.setClassifier(classifier);
//
//        return processor;
//    }

//    @Bean
//    public ItemWriter calculateLogWriter(){
//        ClassifierCompositeItemWriter<ApiRequestVO> writer = new ClassifierCompositeItemWriter<>();
//        //ProcessorClassifier는 Key는 Classifer, value는 반환할 T이다. 객체에 맞게 프로세서슬 반환해주려고 하는것이 목적이다.
//        WriterClassifier<ApiRequestVO, ItemWriter<? super ApiRequestVO>> classifier = new WriterClassifier<>();
//        Map<String, ItemWriter<ApiRequestVO>> writerMap = new HashMap<>();
//        writerMap.put("500", null);
//
//        classifier.setWriterMap(writerMap);
//
//        writer.setClassifier(classifier);
//
//        return writer;
//    }
}
