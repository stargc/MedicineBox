package com.zhiyi.medicinebox.controller.sendmessage;

import com.zhiyi.medicinebox.controller.wexin.WeXinAgentController;
import com.zhiyi.medicinebox.entity.po.alarm.ViewAlarm;
import com.zhiyi.medicinebox.entity.po.base.User;
import com.zhiyi.medicinebox.entity.po.sendmsg.SendmessageLog;
import com.zhiyi.medicinebox.entity.po.sendmsg.SendmessageParm;
import com.zhiyi.medicinebox.entity.vo.sendmsg.WXSendEatMedParmBean;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.service.alarm.AlarmService;
import com.zhiyi.medicinebox.service.base.UserService;
import com.zhiyi.medicinebox.service.sendmsg.SendMessageLogService;
import com.zhiyi.medicinebox.service.sendmsg.SendMessageParmService;
import com.zhiyi.medicinebox.strategy.SendMessageStrategy;
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
@RequestMapping("/sendMsg")
public class SendEatMedicineController {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    /**搜索一个小时内 为发送个通知的 Alarm*/
    private long time = 60 * 60 *1000;

    @Autowired
    private HttpServletRequest request;

    @Resource
    private SendMessageParmService service;

    @Resource
    private WeXinAgentController wxController;

    @Resource
    private SendMessageStrategy sendMessageStrategy;

    @Resource
    private SendMessageParmService parmService;

    @Resource
    private AlarmService alarmService;

    @Resource
    private UserService userService;

    @RequestMapping("/sendWXEatMed")
    @ResponseBody
    public ParmResponse sendWXEatMed() throws IOException {
        ParmResponse tokenResult = wxController.getWinXinToken();
        String access_token = JSONUtils.getString(tokenResult.getData().toString(), "access_token");
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
                        sendMessageStrategy.sendWXEaitMessage(alarmlist.get(i), parms.get(0),access_token,pageUrl);
                    } catch (Exception e) {
                        logger.error(ExceptionUtils.getStackTrace(e));                    }
                }
            }
        }
        logger.info("发送吃药提醒--完成");
        return ResponseUtils.getBooleanResponse(true,"");
    }
}
