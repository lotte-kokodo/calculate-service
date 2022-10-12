package shop.kokodo.calculateservice.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * packageName    : shop.kokodo.calculateservice.tasklet
 * fileName       : ApiStartTasklet
 * author         : namhyeop
 * date           : 2022/09/27
 * description    :
 * Sptring Batch Tasklet
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        namhyeop       최초 생성
 */

@Component
public class ApiStartTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        System.out.println(" >> ApiService is start");

        return RepeatStatus.FINISHED;
    }
}
