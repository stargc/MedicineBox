package com.zhiyi.medicinebox.api.configuration;

import com.zhiyi.medicinebox.api.application.job.SendWXEatMedicineJob;
import com.zhiyi.medicinebox.api.application.job.CleanWXSendMsgParmJob;
import com.zhiyi.medicinebox.api.application.job.SimpleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guanchen
 * @version $Id QuartzConfig.java, v 0.1 2018-11-01 16:38 star Exp $$
 */

@Configuration
public class QuartzConfig {

    @Value("${context_type}")
    private String contextType;

    @Bean
    public JobDetail SimpleJob() {
        return JobBuilder.newJob(SimpleJob.class).withIdentity("SimpleJob")
                .storeDurably().build();
    }

    @Bean
    public JobDetail sendWXEatMedicineJob() {
        return JobBuilder.newJob(SendWXEatMedicineJob.class).withIdentity("SendWXEatMedicine")
                .storeDurably().build();
    }
    @Bean
    public Trigger trigger() {
        JobDetail job = null;
        if ("prod".equals(contextType)){
            job = sendWXEatMedicineJob();
        } else {
            job = SimpleJob();
        }
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24).repeatForever();
        return TriggerBuilder.newTrigger().forJob(job).withIdentity("mytrigger1").withSchedule(scheduleBuilder).build();
    }


    @Bean
    public JobDetail cleanWXSendMsgParmJob() {
        return JobBuilder.newJob(CleanWXSendMsgParmJob.class).withIdentity("cleanWXSendMsgParm")
                .storeDurably().build();
    }

    @Bean
    public Trigger trigger2() {
        JobDetail job = null;
        if ("prod".equals(contextType)){
            job = cleanWXSendMsgParmJob();
        } else {
            job = SimpleJob();
        }
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(10).repeatForever();
        return TriggerBuilder.newTrigger().forJob(job).withIdentity("mytrigger2").withSchedule(scheduleBuilder).build();
    }
}
