package com.zhiyi.medicinebox.controller.alarm;

import com.zhiyi.medicinebox.service.AlarmService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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
        //判断文件是否存在如果不存在就返回默认图标
        if(!(file.exists() && file.canRead())) {
        	return;
        }
        FileInputStream inputStream = new FileInputStream(file);
        BufferedImage bufferedImage = ImageIO.read(file);
        response.setContentType("image/png");
        OutputStream stream = response.getOutputStream();
		OutputStream os = response.getOutputStream();
		ImageIO.write(bufferedImage, "png", os);
    }
}
