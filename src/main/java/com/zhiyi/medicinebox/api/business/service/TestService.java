package com.zhiyi.medicinebox.api.business.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author guanchen
 * @version $Id TestService.java, v 0.1 2018-11-26 17:11 star Exp $$
 */
@Service
@Slf4j
public class TestService {
    @Async("workTaskExecutor")
    public void test(String test){
        log.info("start executeAsync:" + test);
        try{
            Thread.sleep(10000);
        }catch(Exception e){
            e.printStackTrace();
        }
        log.info("end executeAsync:" + test);
    }
}
