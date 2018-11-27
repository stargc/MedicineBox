package com.zhiyi.medicinebox.api.business.service.weixin;

import com.zhiyi.medicinebox.api.business.common.constant.Consts;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author guanchen
 * @version $Id AlarmStrategy.java, v 0.1 2018-07-19 17:49 star Exp $$
 */
@Service
@Slf4j
public class SendMessageService {

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

    @Value("${eat_medicine_notification_skip_page}")
    private String eatMedicineNotificationSkipPage;


    /***
     * 发送微信吃药提醒
     * 此方法进入异步线程池
     * @param alarm
     * @param parm 微信用于发送消息的相关参数
     * @param access_token
     * @param pageUrl
     * @return
     */
    @Async("workTaskExecutor")
    public boolean sendWXEatMessage(ViewAlarm alarm, SendmessageParm parm, String access_token, String pageUrl) {
        if (alarm != null && parm != null) {
            String formId = "";
            if (parm.getFormId() != null) {
                formId = parm.getFormId();
            } else if (parm.getPrepayId() != null) {
                formId = parm.getPrepayId();
            }
            if (!"".equals(formId)) {
                log.info("开始发送用药提醒到用户: alarmID:" + alarm.getAlarmId() +
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
                log.info("发送用药alarmID: " + alarm.getAlarmId() +"提醒结果：" + done);
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
