package com.zhiyi.medicinebox.controller;

import com.zhiyi.medicinebox.entity.alarm.Alarm;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.service.alarm.AlarmService;
import com.zhiyi.medicinebox.util.ResponseUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/alarm")
public class TestController {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired  
	private HttpServletRequest request;

	@Resource
	private AlarmService alarmService;

    @RequestMapping("/getAlarm")
    @ResponseBody
    public ParmResponse getAlarm(int alarmId) throws IOException {
        Alarm alarm = alarmService.findByid(alarmId);
        return ResponseUtils.getBeanResponse(alarm, "",request);

    }
}
