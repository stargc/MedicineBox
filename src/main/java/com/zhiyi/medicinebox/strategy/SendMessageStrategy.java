package com.zhiyi.medicinebox.strategy;

import com.zhiyi.medicinebox.constant.Consts;
import com.zhiyi.medicinebox.entity.po.sendmsg.SendmessageLog;
import com.zhiyi.medicinebox.entity.vo.sendmsg.WXSendEatMedParmBean;
import com.zhiyi.medicinebox.service.alarm.AlarmService;
import com.zhiyi.medicinebox.service.sendmsg.SendMessageLogService;
import com.zhiyi.medicinebox.service.sendmsg.SendMessageParmService;
import com.zhiyi.medicinebox.util.tools.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author guanchen
 * @version $Id AlarmStrategy.java, v 0.1 2018-07-19 17:49 star Exp $$
 */
@Service
public class SendMessageStrategy {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Resource
    private WXStrategy wxStrategy;
    @Resource
    private AlarmService alarmService;
    @Resource
    private SendMessageParmService service;
    @Resource
    private SendMessageLogService sendLogService;

    /***
     * 发送微信吃药提醒
     * @param bean
     * @param access_token
     * @param pageUrl
     * @return
     */
    public boolean sendWXEaitMessage(WXSendEatMedParmBean bean, String access_token, String pageUrl) {
        if (bean != null) {
            String formId = "";
            if (bean.getFormId() != null) {
                formId = bean.getFormId();
            } else if (bean.getPrepayId() != null) {
                formId = bean.getPrepayId();
            }
            if (!"".equals(formId)) {
                logger.info("发送用药提醒到用户: alarmID:" + bean.getAlarmId() +
                        ";药品名：" + bean.getMedName() + "; 提醒时间： " + bean.getAlarmTime() +
                        "; UserID： " + bean.getUserId() + "; userName: " + bean.getUserName());

                String page = new StringBuffer(pageUrl).append("?medId=")
                        .append(bean.getMedId())
                        .append("&alarmId=")
                        .append(bean.getAlarmId())
                        .append("&dosage=")
                        .append(bean.getDosage()).toString();
                boolean done = wxStrategy.sendEatMedicinePush(access_token, bean.getOpenId(),
                        page, formId, bean.getMedName(), bean.getAlarmTime(), bean.getDosage());

                if (done) {
                    // 设置该条提醒已经发送
                    alarmService.updateIsSend(bean.getAlarmId(), (short) 1);
                    // 设置这个form 为 已使用
                    service.updateIsSend((short) 1, bean.getParmId());
                    // 记录日志
                    SendmessageLog log = new SendmessageLog();
                    log.setUserId(bean.getUserId());
                    log.setUserName(bean.getUserName());
                    log.setOpenId(bean.getOpenId());
                    log.setMessage("发送用药提醒到用户: alarmID:" + bean.getAlarmId() + ": 药品名："
                            + bean.getMedName() + "; 提醒时间： " + DateUtil.date2String(bean.getAlarmTime()) + "; UserID： "
                            + bean.getUserId() + "; userName: " + bean.getUserName());
                    log.setType(Consts.SEND_MESSAGE_LOG_TYPE);
                    log.setCreateDate(new Date());
                    sendLogService.add(log);
                }
            }
        }
        return false;
    }
}
