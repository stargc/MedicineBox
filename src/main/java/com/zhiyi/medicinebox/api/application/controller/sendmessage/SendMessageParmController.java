package com.zhiyi.medicinebox.api.application.controller.sendmessage;

import com.zhiyi.medicinebox.api.business.common.constant.ResultCode;
import com.zhiyi.medicinebox.api.business.common.vo.ParmResponse;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.SendmessageParmMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.SendmessageParm;
import com.zhiyi.medicinebox.api.infrastructure.util.ResponseUtils;
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
	public ParmResponse deleteByDate(Date date){
		int num = sendmessageParmMapper.deleteByDate(date);
		return ResponseUtils.getBeanResponse(num, "成功删除数量" + num);
	}

	@RequestMapping("/addParm")
	@ResponseBody
	public ParmResponse addParm(SendmessageParm parm){
		if (parm != null && !StringUtils.isEmpty(parm.getOpenId())) {
			boolean done = sendmessageParmMapper.insertSelective(parm) > 0;
			return ResponseUtils.getBooleanResponse(done, "");
		}
		return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "参数错误");
	}
}
