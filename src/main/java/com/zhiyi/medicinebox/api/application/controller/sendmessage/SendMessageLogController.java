package com.zhiyi.medicinebox.api.application.controller.sendmessage;

import com.zhiyi.medicinebox.api.business.common.constant.ResultCode;
import com.zhiyi.medicinebox.api.business.common.vo.ParmResponse;
import com.zhiyi.medicinebox.api.business.service.sendmsg.SendMessageLogService;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.SendmessageLog;
import com.zhiyi.medicinebox.api.infrastructure.util.ResponseUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/SendMessageLog")
public class SendMessageLogController {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    private HttpServletRequest request;

    @Resource
    private SendMessageLogService sendService;

    @RequestMapping("/add")
    @ResponseBody
    public ParmResponse addSendMessageLog(SendmessageLog log) {
        if (log == null || log.getUserId() == null) {
            return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "参数错误");
        }
        boolean done = sendService.add(log);
        return ResponseUtils.getBooleanResponse(done, "");
    }
}
