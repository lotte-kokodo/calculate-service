package shop.kokodo.calculateservice.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shop.kokodo.calculateservice.listener.JobTimeListener;
import shop.kokodo.calculateservice.tasklet.ApiEndTasklet;
import shop.kokodo.calculateservice.tasklet.ApiStartTasklet;

/**
 * packageName    : shop.kokodo.calculateservice.job
 * fileName       : OrderJobConfiguration
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class CalculateJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ApiStartTasklet apiStartTasklet;
    private final ApiEndTasklet apiEndTasklet;
    private final Step calculateChildJobStep;

    @Bean
    public Job calculateParentJob(){
        return jobBuilderFactory.get("calculateParentJob")
                .listener(new JobTimeListener())
                .incrementer(new RunIdIncrementer())
                .start(startSignalStep())
                .next(calculateChildJobStep)
                .next(endSignalStep())
                .build();
    }

    @Bean
    public Step startSignalStep(){
        return stepBuilderFactory.get("startSignalStep")
                .tasklet(apiStartTasklet)
                .build();
    }

    @Bean
    public Step endSignalStep(){
        return stepBuilderFactory.get("endSignalStep")
                .tasklet(apiEndTasklet)
                .build();
    }
}
