package com.zhiyi.medicinebox.api.application.controller.sendmessage;

import com.zhiyi.medicinebox.api.business.common.vo.ParmResponse;
import com.zhiyi.medicinebox.api.business.strategy.SendMessageStrategy;
import com.zhiyi.medicinebox.api.infrastructure.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author guanchen
 * @version $Id SendEatMedicineController.java, v 0.1 2018-04-17 20:19 Administrator Exp $$
 */

@RestController
@RequestMapping("/sendMsg")
@Slf4j
public class SendEatMedicineController {
    @Resource
    private SendMessageStrategy sendMessageStrategy;

    @RequestMapping("/sendWXEatMed")
    @ResponseBody
    public ParmResponse sendWXEatMed(int time) throws IOException {
        sendMessageStrategy.startSendEatMassage(time*60*1000);
        log.info("发送吃药提醒--完成");
        return ResponseUtils.getBooleanResponse(true,"");
    }
}
