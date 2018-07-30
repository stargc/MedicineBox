package com.zhiyi.medicinebox.job;

import com.zhiyi.medicinebox.service.sendmsg.SendMessageParmService;
import com.zhiyi.medicinebox.strategy.AlarmStrategy;
import com.zhiyi.medicinebox.util.http.HTTPUtils;
import com.zhiyi.medicinebox.util.tools.ConfigUtil;
import com.zhiyi.medicinebox.util.tools.DateUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guanchen
 * @version $Id BaseQuartzJob.java, v 0.1 2018-04-17 20:29 Administrator Exp $$
 */
@Component
public class BaseQuartzJob {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Resource
    private SendMessageParmService sendParmService;

    public void quaryDB() {
        logger.info( "定时 调用数据库刷新连接状态");
        String method = "StatusMed/findById.do";
        try {
            Map<String, Object> parms = new HashMap<>();
            parms.put("id", "1");
            String url = ConfigUtil.getValue("local_host") + method;
//            HTTPUtils.sendPostRequest(url,parms);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    public void cleanSendMsgParm() {
        logger.info( "定时清理 SendMessageParm 中过期的数据");
        String method = "SendMessageParm/deleteOverdueParm.do";
        try {
            Map<String, Object> parms = new HashMap<>();
            Date date = new Date(System.currentTimeMillis() - 7*24*60*60*1000);
            sendParmService.deleteByDate(date);
            parms.put("date", DateUtil.date2String(date));
            String url = ConfigUtil.getValue("local_host") + method;
//            HTTPUtils.sendPostRequest(url,parms);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    public void sendEatMedicine() {
        logger.info( "定时 用户吃药提醒定时任务 -- 开启");
        String method = "sendMsg/sendWXEatMed.do";
        try {
            Map<String, Object> parms = new HashMap<>();
            String url = ConfigUtil.getValue("local_host") + method;
//            HTTPUtils.sendPostRequest(url,parms);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
