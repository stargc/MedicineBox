package com.zhiyi.medicinebox.api.application.controller;

import com.zhiyi.medicinebox.api.business.common.vo.ParmResponse;
import com.zhiyi.medicinebox.api.business.service.alarm.AlarmService;
import com.zhiyi.medicinebox.api.business.service.alarm.RecordService;
import com.zhiyi.medicinebox.api.business.service.base.MedicineService;
import com.zhiyi.medicinebox.api.business.service.base.UserService;
import com.zhiyi.medicinebox.api.business.service.sendmsg.SendMessageLogService;
import com.zhiyi.medicinebox.api.business.service.sendmsg.SendMessageParmService;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Alarm;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.User;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.ViewAlarm;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.ViewRecord;
import com.zhiyi.medicinebox.api.infrastructure.util.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class TestController {


    @Autowired
    private HttpServletRequest request;

    @Resource
    private AlarmService alarmService;
    @Resource
    private RecordService recordService;
    @Resource
    private MedicineService medicineService;
    @Resource
    private UserService userService;
    @Resource
    private SendMessageLogService sendMessageLogService;
    @Resource
    private SendMessageParmService sendMessageParmService;


    @RequestMapping("/test")
    @ResponseBody
    public ParmResponse test(String name) throws IOException {
        User u = userService.findByOpenId("otucO0WaKm9S6-mkGdFqXiCVkgvg");
        Alarm a = alarmService.findByid(13);
        List<ViewRecord> list = recordService.findViewRecordByUserId(32,new Date());
        List<ViewAlarm> list1 = alarmService.findViewAlarmByUserId(32,new Date());
        return ResponseUtils.getBeanResponse("SUCCESS", "");
    }

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "";
        String s3 = " ";
        String s4 = null;
        System.out.println(StringUtils.isBlank(s1) + "  " + StringUtils.isEmpty(s1));
        System.out.println(StringUtils.isBlank(s2) + "  " + StringUtils.isEmpty(s2));
        System.out.println(StringUtils.isBlank(s3) + "  " + StringUtils.isEmpty(s3));
        System.out.println(StringUtils.isBlank(s4) + "  " + StringUtils.isEmpty(s4));
    }
}
