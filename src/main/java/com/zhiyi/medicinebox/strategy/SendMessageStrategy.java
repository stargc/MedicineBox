package com.zhiyi.medicinebox.strategy;

import com.zhiyi.medicinebox.constant.Consts;
import com.zhiyi.medicinebox.entity.po.alarm.ViewAlarm;
import com.zhiyi.medicinebox.entity.po.sendmsg.SendmessageLog;
import com.zhiyi.medicinebox.entity.po.sendmsg.SendmessageParm;
import com.zhiyi.medicinebox.service.alarm.AlarmService;
import com.zhiyi.medicinebox.service.sendmsg.SendMessageLogService;
import com.zhiyi.medicinebox.service.sendmsg.SendMessageParmService;
import com.zhiyi.medicinebox.util.tools.ConfigUtil;
import com.zhiyi.medicinebox.util.tools.DateUtil;
import com.zhiyi.medicinebox.util.tools.JSONUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    private SendMessageLogService sendLogService;
    @Resource
    private SendMessageParmService parmService;

    /***
     * 查询 time 秒内 所有的alarm,并发送提醒到指定用户
     * @param time 单位秒
     */
    public void startSendEatMassage(long time){

        String tokenResult = wxStrategy.getWinXinToken();
        String access_token = JSONUtils.getString(tokenResult, "access_token");
        String pageUrl = ConfigUtil.getValue("eat_medicine_notification_skip_page");
        Date today = new Date();
        Date startTime = new Date(today.getTime() - time);

        List<ViewAlarm> alarmlist = alarmService.findAlarmToSendMsg(startTime,today);
        logger.info("共需发送用药提醒：" + alarmlist != null ? alarmlist.size() : "0" + "条");
        if (alarmlist != null && !alarmlist.isEmpty()){
            for (int i = 0; i < alarmlist.size(); i++) {
                List<SendmessageParm> parms = parmService.findByUserId(alarmlist.get(i).getUserId());
                if (parms != null && !parms.isEmpty()){

                    try {
                        sendWXEatMessage(alarmlist.get(i), parms.get(0),access_token,pageUrl);
                    } catch (Exception e) {
                        logger.error(ExceptionUtils.getStackTrace(e));                    }
                }
            }
        }
    }

    /***
     * 发送微信吃药提醒
     * @param alarm
     * @param parm 微信用于发送消息的相关参数
     * @param access_token
     * @param pageUrl
     * @return
     */
    public boolean sendWXEatMessage(ViewAlarm alarm, SendmessageParm parm, String access_token, String pageUrl) {
        if (alarm != null && parm != null) {
            String formId = "";
            if (parm.getFormId() != null) {
                formId = parm.getFormId();
            } else if (parm.getPrepayId() != null) {
                formId = parm.getPrepayId();
            }
            if (!"".equals(formId)) {
                logger.info("开始发送用药提醒到用户: alarmID:" + alarm.getAlarmId() +
                        ";药品名：" + alarm.getMedName() + "; 提醒时间： " + alarm.getAlarmTime() +
                        "; UserID： " + alarm.getUserId() + "; userName: " + alarm.getUserName());

                String page = new StringBuffer(pageUrl).append("?medId=")
                        .append(alarm.getMedId())
                        .append("&alarmId=")
                        .append(alarm.getAlarmId())
                        .append("&dosage=")
                        .append(alarm.getDosage()).toString();
                boolean done = wxStrategy.sendEatMedicinePush(access_token, alarm.getOpenId(),
                        page, formId, alarm.getMedName(), alarm.getAlarmTime(), alarm.getDosage());
                logger.info("发送用药alarmID: " + alarm.getAlarmId() +"提醒结果：" + done);
                if (done) {
                    // 设置该条提醒已经发送
                    alarmService.updateIsSend(alarm.getAlarmId(), (short) 1);
                    // 设置这个form 为 已使用
                    parmService.updateIsSend((short) 1, parm.getId());
                    // 记录日志
                    SendmessageLog log = new SendmessageLog();
                    log.setUserId(alarm.getUserId());
                    log.setUserName(alarm.getUserName());
                    log.setOpenId(alarm.getOpenId());
                    log.setMessage("发送用药提醒到用户: alarmID:" + alarm.getAlarmId() + ": 药品名："
                            + alarm.getMedName() + "; 提醒时间： " + DateUtil.date2String(alarm.getAlarmTime()) + "; UserID： "
                            + alarm.getUserId() + "; userName: " + alarm.getUserName());
                    log.setType(Consts.SEND_MESSAGE_LOG_TYPE);
                    log.setCreateDate(new Date());
                    sendLogService.add(log);
                }
            }
        }
        return false;
    }
}
