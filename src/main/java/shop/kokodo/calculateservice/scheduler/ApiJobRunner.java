//TODO: 배치 스케줄러, 배포시 주석해제
package shop.kokodo.calculateservice.scheduler;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ApiJobRunner extends JobRunner{

    @Autowired
    private Scheduler scheduler;

    @Override
    protected void doRun(ApplicationArguments args) {
        JobDetail jobDetail = buildJobDetail(ApiSchJob.class, "calculateParentJob", "batch", new HashMap<>());
        //CronExpression을 사용, 맨 앞에서부터 "초", "분", "시", "일", "주", "월"을 의미함
        Trigger trigger = buildJobTrigger("0/10 * * * * ?");
//        Trigger trigger = buildJobTrigger("0 10 0 * * ?");

        try{
            //이 곳에서 실제로 Quarz가 실행된다, JobDeatail에는 Job에대한 정보가 들어있고 Trigger에는 언제 동작할 지에 대한 정보가 담겨있다.
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
