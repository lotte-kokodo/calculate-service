package shop.kokodo.calculateservice.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * packageName    : shop.kokodo.calculateservice.tasklet
 * fileName       : ApiEndTasklet
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * Sptring Batch Tasklet
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */
@Slf4j
@Component
public class ApiEndTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        log.info(" >> ApiService is end");
        return RepeatStatus.FINISHED;
    }
}
