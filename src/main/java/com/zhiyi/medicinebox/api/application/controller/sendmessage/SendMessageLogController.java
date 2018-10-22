package com.zhiyi.medicinebox.api.application.controller.sendmessage;

import com.zhiyi.medicinebox.api.business.common.constant.ResultCode;
import com.zhiyi.medicinebox.api.business.common.vo.ParmResponse;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.SendmessageLogMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.SendmessageLog;
import com.zhiyi.medicinebox.api.infrastructure.util.ResponseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/SendMessageLog")
public class SendMessageLogController {

    @Resource
    private SendmessageLogMapper sendmessageLogMapper;

    @RequestMapping("/add")
    @ResponseBody
    public ParmResponse addSendMessageLog(SendmessageLog log) {
        if (log == null || log.getUserId() == null) {
            return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "参数错误");
        }
        boolean done = sendmessageLogMapper.insertSelective(log) > 0;
        return ResponseUtils.getBooleanResponse(done, "");
    }
}
