package com.zhiyi.medicinebox.controller.sendmessage;

import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.strategy.SendMessageStrategy;
import com.zhiyi.medicinebox.util.ResponseUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author guanchen
 * @version $Id SendEatMedicineController.java, v 0.1 2018-04-17 20:19 Administrator Exp $$
 */

@Controller
@RequestMapping("/sendMsg")
public class SendEatMedicineController {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());


    @Autowired
    private HttpServletRequest request;

    @Resource
    private SendMessageStrategy sendMessageStrategy;

    @RequestMapping("/sendWXEatMed")
    @ResponseBody
    public ParmResponse sendWXEatMed(int time) throws IOException {
        sendMessageStrategy.startSendEatMassage(time*60*1000);
        logger.info("发送吃药提醒--完成");
        return ResponseUtils.getBooleanResponse(true,"");
    }
}
