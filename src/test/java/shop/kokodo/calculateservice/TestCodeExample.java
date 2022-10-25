//import groovy.util.logging.Slf4j;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.batch.test.context.SpringBatchTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import shop.kokodo.calculateservice.TestBatchConfig;
//import shop.kokodo.calculateservice.job.CalculateJobConfiguration;
//
//import java.time.format.DateTimeFormatter;
//
//import static java.time.format.DateTimeFormatter.ofPattern;
//
//@Slf4j
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = {TestBatchConfig.class, CalculateJobConfiguration.class})
//@SpringBatchTest
//public class TestCodeExample {
//    public static final DateTimeFormatter FORMATTER = ofPattern("yyyy-MM-dd");
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private ProductBackupRepository productBackupRepository;
//
//    @Autowired
//    private JobLauncherTestUtils jobLauncherTestUtils;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @AfterEach
//    public void after() throws Exception {
//        productRepository.deleteAllInBatch();
//        productBackupRepository.deleteAllInBatch();
//    }
//
//    @Test
//    void H2_Product가_ProductBackup으로_이관된다() throws Exception {
//        //given
//        LocalDate txDate = LocalDate.of(2021,1,12);
//
//        List<Product> products = new ArrayList<>();
//        int expectedCount = 50;
//        for (int i = 1; i <= expectedCount; i++) {
//            products.add(new Product(i, txDate));
//        }
//        productRepository.saveAll(products);
//
//        JobParameters jobParameters = jobLauncherTestUtils.getUniqueJobParametersBuilder()
//                .addString("startDate", txDate.format(FORMATTER))
//                .addString("endDate", txDate.plusDays(1).format(FORMATTER))
//                .toJobParameters();
//
//        //when
//        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
//
//        //then
//        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
//        List<ProductBackup> backups = productBackupRepository.findAll();
//        assertThat(backups.size()).isEqualTo(expectedCount);
//
//        List<Map<String, Object>> metaTable = jdbcTemplate.queryForList("select step_name, status, commit_count, read_count, write_count from BATCH_STEP_EXECUTION");
//
//        for (Map<String, Object> step : metaTable) {
//            log.info("meta table row={}", step);
//        }
//    }
//}