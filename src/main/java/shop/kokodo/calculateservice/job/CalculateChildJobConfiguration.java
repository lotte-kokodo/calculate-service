package shop.kokodo.calculateservice.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : shop.kokodo.calculateservice.job
 * fileName       : CalculateChildJobConfiguration
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * CalculateChildJob Config File, Job의 수행을 이어받는다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CalculateChildJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final Step apiMasterStep;
    private final JobLauncher jobLauncher;

    @Bean
    public Step calculateChildJobStep() {
        return stepBuilderFactory.get("calculateChildJobStep")
                .job(childJob())
                .launcher(jobLauncher)
                .build();
    }

    @Bean
    public Job childJob() {
        return jobBuilderFactory.get("childJob")
                .start(apiMasterStep)
                .build();
    }


}
