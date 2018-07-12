package com.zhiyi.medicinebox.controller.alarm;

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
public class AlarmController {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired  
	private HttpServletRequest request;

	@Resource
	private AlarmService alarmService;
		
    @RequestMapping("/getImage")
    public void getIcon(String url,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        logger.info("get image “"+ url + "” from server");
        String fileName = url;
        File file = new File(fileName);
        try {
            //判断文件是否存在如果不存在就返回默认图标
            if (!(file.exists() && file.canRead())) {
                logger.error("alarm/getImage url {} 文件不存在", url);
                return;
            }
            BufferedImage bufferedImage = ImageIO.read(file);
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            ImageIO.write(bufferedImage, "png", os);
        } catch (Exception e){
            logger.error("get image 报错：" + ExceptionUtils.getStackTrace(e));
        }
    }



    @RequestMapping("/getAlarm")
    @ResponseBody
    public ParmResponse getAlarm(int alarmId) throws IOException {
        Alarm alarm = alarmService.findByid(alarmId);
        return ResponseUtils.getBeanResponse(alarm, "",request);

    }
}
