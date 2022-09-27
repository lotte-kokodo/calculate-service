package shop.kokodo.calculateservice.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
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
public class OrderJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ApiStartTasklet apiStartTasklet;
    private final ApiEndTasklet apiEndTasklet;
    private final JobLauncher jobLauncher;
}
