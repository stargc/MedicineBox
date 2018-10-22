package com.zhiyi.medicinebox.api.application.controller.sendmessage;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.SendmessageLogMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.SendmessageLog;
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
    public BaseResponse addSendMessageLog(SendmessageLog log) {
        BaseResponse resp = new BaseResponse();
        if (log == null || log.getUserId() == null) {
            resp.setResultCode(BaseResponse.FAILED);
            resp.setResultMsg("参数错误");
            return resp;
        }
        boolean done = sendmessageLogMapper.insertSelective(log) > 0;
        resp.setResultCode(done ? BaseResponse.SUCCESS : BaseResponse.FAILED);
        return resp;
    }
}
