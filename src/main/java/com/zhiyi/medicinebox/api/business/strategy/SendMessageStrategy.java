package com.zhiyi.medicinebox.api.business.strategy;

import com.zhiyi.medicinebox.api.business.common.constant.Consts;
import com.zhiyi.medicinebox.api.business.service.weixin.WXService;
import com.zhiyi.medicinebox.api.business.service.weixin.vo.WXTokenResp;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.AlarmMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.SendmessageLogMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.SendmessageParmMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.ViewAlarmMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Alarm;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.SendmessageLog;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.SendmessageParm;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.ViewAlarm;
import com.zhiyi.medicinebox.api.infrastructure.util.DateUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
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
    private WXService wxStrategy;

    @Resource
    private AlarmMapper alarmMapper;
    @Resource
    private ViewAlarmMapper viewAlarmMapper;
    @Resource
    private SendmessageParmMapper sendmessageParmMapper;
    @Resource
    private SendmessageLogMapper sendmessageLogMapper;

    @Value("eat_medicine_notification_skip_page")
    private String eatMedicineNotificationSkipPage;

    /***
     * 查询 time 秒内 所有的alarm,并发送提醒到指定用户
     * @param time 单位秒
     */
    public void startSendEatMassage(long time){

        WXTokenResp tokenResult = wxStrategy.getWinXinToken();

        String access_token =  tokenResult.getAccessToken();
        Date today = new Date();
        Date startTime = new Date(today.getTime() - time);

        List<ViewAlarm> alarmlist = viewAlarmMapper.findAlarmToSendMsg(startTime,today);
        logger.info("共需发送用药提醒：" + alarmlist != null ? alarmlist.size() : "0" + "条");
        if (alarmlist != null && !alarmlist.isEmpty()){
            for (int i = 0; i < alarmlist.size(); i++) {
                List<SendmessageParm> parms = sendmessageParmMapper.findByUserId(alarmlist.get(i).getUserId());
                if (parms != null && !parms.isEmpty()){

                    try {
                        sendWXEatMessage(alarmlist.get(i), parms.get(0),access_token,eatMedicineNotificationSkipPage);
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

                    Alarm alarmBean = new Alarm();
                    alarmBean.setAlarmId(alarm.getAlarmId());
                    alarmBean.setIsSend( (short) 1);
                    alarmMapper.updateByPrimaryKeySelective(alarmBean);

                    // 设置这个form 为 已使用
                    SendmessageParm parmBean = new SendmessageParm();
                    parmBean.setId(parm.getId());
                    parmBean.setIsUsed((short) 1);
                    sendmessageParmMapper.updateByPrimaryKeySelective(parmBean);

                    // 记录日志
                    SendmessageLog log = new SendmessageLog();
                    log.setUserId(alarm.getUserId());
                    log.setUserName(alarm.getUserName());
                    log.setOpenId(alarm.getOpenId());
                    log.setMessage("发送用药提醒到用户: alarmID:" + alarm.getAlarmId() + ": 药品名："
                            + alarm.getMedName() + "; 提醒时间： " + DateUtils.getDateStr(alarm.getAlarmTime()) + "; UserID： "
                            + alarm.getUserId() + "; userName: " + alarm.getUserName());
                    log.setType(Consts.SEND_MESSAGE_LOG_TYPE);
                    log.setCreateDate(new Date());
                    sendmessageLogMapper.insertSelective(log);
                }
            }
        }
        return false;
    }
}
