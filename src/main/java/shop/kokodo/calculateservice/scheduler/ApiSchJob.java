////TODO: 배치 스케줄러, 배포시 주석해제
//package shop.kokodo.calculateservice.scheduler;//package shop.kokodo.calculateservice.scheduler;
//
//import lombok.SneakyThrows;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * Quartz에도 Job이라는 개념이 존재한다. Batch의 Job과 완벽히 똑같은개념을 아니나 Schedule을 조정하는데 있어서는 유사하다.
// * 그렇기에 Quartz에서 제공하는 인터페이스를 상속받아 어떻게 동작할지 executeInternal에 정의한다.
// */
//
//@Component
//public class ApiSchJob extends QuartzJobBean {
//
//    @Autowired
//    private Job calculateParentJob;
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @SneakyThrows
//    @Override
//    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("id", new Date().getTime())
//                .addString("startDate", "1964-06-09 12:12:12")
//                .addString("endDate", "2099-06-16 12:12:12")
//                .toJobParameters();
//
//        jobLauncher.run(calculateParentJob, jobParameters);
//    }
//}
