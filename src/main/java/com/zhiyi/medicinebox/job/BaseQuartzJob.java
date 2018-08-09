package com.zhiyi.medicinebox.job;

import com.zhiyi.medicinebox.controller.sendmessage.SendEatMedicineController;
import com.zhiyi.medicinebox.service.sendmsg.SendMessageParmService;
import com.zhiyi.medicinebox.strategy.AlarmStrategy;
import com.zhiyi.medicinebox.strategy.SendMessageStrategy;
import com.zhiyi.medicinebox.util.http.HTTPUtils;
import com.zhiyi.medicinebox.util.tools.ConfigUtil;
import com.zhiyi.medicinebox.util.tools.DateUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guanchen
 * @version $Id BaseQuartzJob.java, v 0.1 2018-04-17 20:29 Administrator Exp $$
 */
@Component
public class BaseQuartzJob extends QuartzJobBean {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Resource
    private SendMessageParmService sendParmService;

    @Resource
    private SendMessageStrategy sendMessageStrategy;

    public void quaryDB() {
        logger.info( "定时 调用数据库刷新连接状态");
        String method = "StatusMed/findById.do";
        try {
            Map<String, String> parms = new HashMap<>();
            parms.put("id", "1");
            String url = ConfigUtil.getValue("local_host") + method;
            HTTPUtils.sendGet(url,parms);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    public void cleanSendMsgParm() {
        logger.info( "定时清理 SendMessageParm 中过期的数据");
        Date scrapDate = new Date(System.currentTimeMillis() - 30*24*60*60*1000);
        sendParmService.scrapParm(scrapDate);
        Date deleteDate = new Date(System.currentTimeMillis() - 30*24*60*60*1000);
        sendParmService.deleteByDate(deleteDate);
    }

    public void sendEatMedicine() {
        logger.info( "定时 用户吃药提醒定时任务 -- 开启");

        /**搜索一个小时内 为发送个通知的 Alarm*/
        long time = 60 * 60 *1000;
        sendMessageStrategy.startSendEatMassage(time);
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

    }
}
