package com.zhiyi.medicinebox.controller.sendmessage;

import com.zhiyi.medicinebox.constant.ResultCode;
import com.zhiyi.medicinebox.controller.wexin.WeXinAgentController;
import com.zhiyi.medicinebox.entity.Alarm;
import com.zhiyi.medicinebox.entity.SendMessageLog;
import com.zhiyi.medicinebox.entity.SendMessageParm;
import com.zhiyi.medicinebox.entity.sendMeg.WXSendEatMedParmBean;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.service.AlarmService;
import com.zhiyi.medicinebox.service.SendMessageLogService;
import com.zhiyi.medicinebox.service.SendMessageParmService;
import com.zhiyi.medicinebox.util.ResponseUtils;
import com.zhiyi.medicinebox.util.tools.ConfigUtil;
import com.zhiyi.medicinebox.util.tools.JSONUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author guanchen
 * @version $Id SendEatMedicineController.java, v 0.1 2018-04-17 20:19 Administrator Exp $$
 */

@Controller
@RequestMapping("/sendEatMed")
public class SendEatMedicineController {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    /**搜索一个小时内 为发送个通知的 Alarm*/
    private long time = 60 * 60 *1000;

    @Autowired
    private HttpServletRequest request;

    @Resource
    private SendMessageParmService service;

    @Resource
    private SendMessageLogService sendLogService;

    @Resource
    private AlarmService alarmService;

    @Resource
    private WeXinAgentController wxController;

    @RequestMapping("/send")
    @ResponseBody
    public ParmResponse sendEatMed() throws IOException {
        ParmResponse tokenResult = wxController.getWinXinToken();
        String access_token = JSONUtils.getString(tokenResult.getData().toString(), "access_token");
        String pageUrl = ConfigUtil.getValue("eat_medicine_notification_skip_page");
        Date today = new Date();
        Date startTime = new Date(today.getTime() - time);
        List<WXSendEatMedParmBean> list = service.findSendEatMedParm(today, startTime, today);
        if (list != null && list.size() != 0) {
            logger.info("发送用户吃药提醒 -- 存在任务数量：" + list.size());
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                try {
                    final WXSendEatMedParmBean bean = (WXSendEatMedParmBean) iterator.next();
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
                            final ParmResponse sendResult = wxController.sendEatMedicinePush(access_token, bean.getOpenId(),
                                    page, formId, bean.getMedName(), bean.getAlarmTime(), bean.getDosage());

                            String success = JSONUtils.getString(sendResult.getData().toString(), "errcode");
                            if ("0".equals(success)) {
                                // 设置该条提醒已经发送
                                Alarm alarm = new Alarm();
                                alarm.setAlarmId(bean.getAlarmId());
                                alarm.setIssend(1);
                                alarmService.updateIsSend(alarm);
                                // 设置这个form 为 已使用
                                SendMessageParm sendBean = new SendMessageParm();
                                sendBean.setId(Integer.valueOf(bean.getParmId1()));
                                sendBean.setIsused(1);
                                service.updateIsSend(sendBean);
                                // 记录日志
                                SendMessageLog log = new SendMessageLog();
                                log.setUserId(bean.getUserId());
                                log.setUserName(bean.getUserName());
                                log.setOpenId(bean.getOpenId());
                                log.setMessage("发送用药提醒到用户: alarmID:" + bean.getAlarmId() + ": 药品名："
                                        + bean.getMedName() + "; 提醒时间： " + bean.getAlarmTime() + "; UserID： "
                                        + bean.getUserId() + "; userName: " + bean.getUserName());
                                log.setType("EATMED");
                                log.setCreateDate(new Date());
                                sendLogService.add(log);
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error(ExceptionUtils.getStackTrace(e));
                }
            }
        }
        logger.info("发送吃药提醒--完成");
        return ResponseUtils.getBooleanResponse(true,"",request);
    }
}
