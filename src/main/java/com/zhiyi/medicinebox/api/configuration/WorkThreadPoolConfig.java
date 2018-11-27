package com.zhiyi.medicinebox.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 用于处理复杂不限时业务逻辑
 *
 * @author guanchen
 * @version $Id ThreadPoolConfig.java, v 0.1 2018-11-26 15:09 star Exp $$
 */
@Configuration
@EnableAsync
public class WorkThreadPoolConfig {
    @Bean(name="workTaskExecutor")
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setKeepAliveSeconds(300);
        pool.setCorePoolSize(3);//核心线程池数
        pool.setMaxPoolSize(10); // 最大线程
        pool.setQueueCapacity(9999);//队列容量
        pool.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy()); //队列满，线程被拒绝执行策略
        return pool;
    }
}
