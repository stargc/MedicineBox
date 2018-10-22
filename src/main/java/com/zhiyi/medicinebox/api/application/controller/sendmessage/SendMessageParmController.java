package com.zhiyi.medicinebox.api.application.controller.sendmessage;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.SendmessageParmMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.SendmessageParm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/SendMessageParm")
public class SendMessageParmController {

    @Resource
    private SendmessageParmMapper sendmessageParmMapper;

    @RequestMapping("/deleteOverdueParm")
    @ResponseBody
    public BaseResponse deleteByDate(Date date) {
        int num = sendmessageParmMapper.deleteByDate(date);

        BaseResponse resp = new BaseResponse();
        resp.setResultCode(BaseResponse.SUCCESS);
        resp.setResultMsg("删除成功" + num + "条");
        return resp;
    }

    @RequestMapping("/addParm")
    @ResponseBody
    public BaseResponse addParm(SendmessageParm parm) {
        BaseResponse resp = new BaseResponse();
        if (parm != null && !StringUtils.isEmpty(parm.getOpenId())) {
            boolean done = sendmessageParmMapper.insertSelective(parm) > 0;

            resp.setResultCode(done ? BaseResponse.SUCCESS : BaseResponse.FAILED);
            return resp;
        }
        resp.setResultCode(BaseResponse.FAILED);
        resp.setResultMsg("参数错误");
        return resp;


    }
}
