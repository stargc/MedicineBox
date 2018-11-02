package com.zhiyi.medicinebox.api.application.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author guanchen
 * @version $Id MyJob.java, v 0.1 2018-11-01 14:38 star Exp $$
 */
@Component
@Slf4j
public class SimpleJob extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("定时");
    }
}

