package com.zhiyi.medicinebox.api.application.job;

import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.SendmessageParmMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author guanchen
 * @version $Id MyJob.java, v 0.1 2018-11-01 14:38 star Exp $$
 */
@Component
@Slf4j
public class CleanWXSendMsgParmJob extends QuartzJobBean {

    @Resource
    private SendmessageParmMapper sendmessageParmMapper;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info( "定时清理 SendMessageParm 中过期的数据");
//        Date scrapDate = new Date(System.currentTimeMillis() - 30*24*60*60*1000);
//        sendmessageParmMapper.scrapParm(scrapDate);
//        Date deleteDate = new Date(System.currentTimeMillis() - 30*24*60*60*1000);
//        sendmessageParmMapper.deleteByDate(deleteDate);
    }
}
