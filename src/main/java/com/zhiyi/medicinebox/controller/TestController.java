package com.zhiyi.medicinebox.controller;

import com.zhiyi.medicinebox.entity.po.alarm.Alarm;
import com.zhiyi.medicinebox.entity.po.alarm.ViewAlarm;
import com.zhiyi.medicinebox.entity.po.alarm.ViewRecord;
import com.zhiyi.medicinebox.entity.po.base.User;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.service.alarm.AlarmService;
import com.zhiyi.medicinebox.service.alarm.RecordService;
import com.zhiyi.medicinebox.service.base.MedicineService;
import com.zhiyi.medicinebox.service.base.UserService;
import com.zhiyi.medicinebox.service.sendmsg.SendMessageLogService;
import com.zhiyi.medicinebox.service.sendmsg.SendMessageParmService;
import com.zhiyi.medicinebox.util.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

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
