package com.zhiyi.medicinebox.api.application.controller.sendmessage;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.business.service.weixin.SendMessageService;
import com.zhiyi.medicinebox.api.business.service.weixin.WXService;
import com.zhiyi.medicinebox.api.business.service.weixin.vo.WXTokenResp;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.SendmessageParmMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.ViewAlarmMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.SendmessageParm;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.ViewAlarm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author guanchen
 * @version $Id SendEatMedicineController.java, v 0.1 2018-04-17 20:19 Administrator Exp $$
 */

@RestController
@RequestMapping("/sendMsg")
@Slf4j
public class SendEatMedicineController {

    @Resource
    private SendMessageService sendMessageStrategy;
    @Resource
    private ViewAlarmMapper viewAlarmMapper;
    @Resource
    private WXService wxStrategy;
    @Resource
    private SendmessageParmMapper sendmessageParmMapper;

    @Value("${eat_medicine_notification_skip_page}")
    private String eatMedicineNotificationSkipPage;

    @RequestMapping("/sendWXEatMed")
    @ResponseBody
    public BaseResponse sendWXEatMed(int time) throws IOException {
        startSendEatMassage(time*60*1000);
        log.info("发送吃药提醒--完成");
        BaseResponse resp = new BaseResponse();
        resp.setResultCode(BaseResponse.SUCCESS);
        return resp;
    }

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
        log.info("共需发送用药提醒：" + (alarmlist != null ? alarmlist.size() : "0") + "条");
        if (alarmlist != null && !alarmlist.isEmpty()){
            for (int i = 0; i < alarmlist.size(); i++) {
                List<SendmessageParm> parms = sendmessageParmMapper.findByUserId(alarmlist.get(i).getUserId());
                if (parms != null && !parms.isEmpty()){

                    try {
                        sendMessageStrategy.sendWXEatMessage(alarmlist.get(i), parms.get(0),access_token,eatMedicineNotificationSkipPage);
                    } catch (Exception e) {
                        log.error(ExceptionUtils.getStackTrace(e));                    }
                }
            }
        }
    }
}
