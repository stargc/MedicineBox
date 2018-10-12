package com.zhiyi.medicinebox.api.application.job;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guanchen
 * @version $Id BaseQuartzJob.java, v 0.1 2018-04-17 20:29 Administrator Exp $$
 */
@Component
@Slf4j
public class BaseQuartzJob extends QuartzJobBean {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

//    @Resource
//    private SendMessageParmService sendParmService;
//
//    @Resource
//    private SendMessageStrategy sendMessageStrategy;

    @Autowired
    private RestTemplate restTemplate;

    @Value("local_host")
    private String localHostUrl;

    public void quaryDB() {
        logger.info( "定时 调用数据库刷新连接状态");
        String method = "StatusMed/findById.do";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> parmMap = new HashMap<>();
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(parmMap, headers);
        log.info("通知鹰眼保存用户信息 请求：{}", parmMap);
        restTemplate.postForObject(localHostUrl+method, entity, String.class);
        log.info("通知鹰眼保存用户信息 响应：{}");
    }

//    public void cleanSendMsgParm() {
//        logger.info( "定时清理 SendMessageParm 中过期的数据");
//        Date scrapDate = new Date(System.currentTimeMillis() - 30*24*60*60*1000);
//        sendParmService.scrapParm(scrapDate);
//        Date deleteDate = new Date(System.currentTimeMillis() - 30*24*60*60*1000);
//        sendParmService.deleteByDate(deleteDate);
//    }
//
//    public void sendEatMedicine() {
//        logger.info( "定时 用户吃药提醒定时任务 -- 开启");
//
//        /**搜索一个小时内 为发送个通知的 Alarm*/
//        long time = 60 * 60 *1000;
//        sendMessageStrategy.startSendEatMassage(time);
//    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

    }
}
