package com.zhiyi.medicinebox.api.configuration;

import com.zhiyi.medicinebox.api.application.job.CleanWXSendMsgParmJob;
import com.zhiyi.medicinebox.api.application.job.SendWXEatMedicineJob;
import org.quartz.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guanchen
 * @version $Id QuartzConfig.java, v 0.1 2018-11-01 16:38 star Exp $$
 */

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail sendWXEatMedicineJob() {
        return JobBuilder.newJob(SendWXEatMedicineJob.class).withIdentity("SendWXEatMedicine")
                .storeDurably().build();
    }
    @Bean
    @ConditionalOnProperty(name = "context_type", havingValue = "prod")
    public Trigger SendWXEatMedicineTrigger() {
        JobDetail job = sendWXEatMedicineJob();
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24).repeatForever();
        return TriggerBuilder.newTrigger().forJob(job).withIdentity("SendWXEatMedicineTrigger").withSchedule(scheduleBuilder).build();
    }


    @Bean
    public JobDetail cleanWXSendMsgParmJob() {
        return JobBuilder.newJob(CleanWXSendMsgParmJob.class).withIdentity("cleanWXSendMsgParm")
                .storeDurably().build();
    }

    @Bean
    @ConditionalOnProperty(name = "context_type", havingValue = "prod")
    public Trigger cleanWXSendMsgParmTrigger() {
        JobDetail job = cleanWXSendMsgParmJob();
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(10).repeatForever();
        return TriggerBuilder.newTrigger().forJob(job).withIdentity("cleanWXSendMsgParmTrigger").withSchedule(scheduleBuilder).build();
    }
}
