package com.zhiyi.medicinebox.api.application.job;

import com.zhiyi.medicinebox.api.business.strategy.SendMessageStrategy;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author guanchen
 * @version $Id MyJob.java, v 0.1 2018-11-01 14:38 star Exp $$
 */
@Component
@Slf4j
public class SendWXEatMedicineJob extends QuartzJobBean {

    @Resource
    private SendMessageStrategy sendMessageStrategy;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("定时 用户吃药提醒定时任务 -- 开启");

        /**搜索一个小时内 为发送个通知的 Alarm*/
        long time = 60 * 60 * 1000;
        sendMessageStrategy.startSendEatMassage(time);
    }
}

