package com.zhiyi.medicinebox.api.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

@Component
public class QuartzJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private ApplicationContext context;

    @Override
    protected Job createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Job jobInstance = (Job)super.createJobInstance(bundle);
        return jobInstance;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
