package com.zhiyi.medicinebox.api.application.job;

import com.zhiyi.medicinebox.api.application.controller.sendmessage.SendEatMedicineController;
import com.zhiyi.medicinebox.api.business.service.weixin.SendMessageService;
import com.zhiyi.medicinebox.api.business.service.weixin.WXService;
import com.zhiyi.medicinebox.api.business.service.weixin.vo.WXTokenResp;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.SendmessageLogMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.SendmessageParmMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.ViewAlarmMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.SendmessageParm;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.ViewAlarm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author guanchen
 * @version $Id MyJob.java, v 0.1 2018-11-01 14:38 star Exp $$
 */
@Component
@Slf4j
public class SendWXEatMedicineJob extends QuartzJobBean {

    @Resource
    private SendEatMedicineController sendEatMedicineController;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("定时 用户吃药提醒定时任务 -- 开启");

        /**搜索一个小时内 未发送通知的 Alarm*/
        long time = 60 * 60 * 1000;
        sendEatMedicineController.startSendEatMassage(time);

    }
}

