package com.zhiyi.medicinebox.controller.sendmessage;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyi.medicinebox.constant.ResultCode;
import com.zhiyi.medicinebox.entity.SendMessageParm;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.service.SendMessageParmService;
import com.zhiyi.medicinebox.util.ResponseUtils;

@Controller
@RequestMapping("/SendMessageParm")
public class SendMessageParmController {

	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired  
	private HttpServletRequest request;

	@Resource
	private SendMessageParmService sendParmService;
	
	@RequestMapping("/deleteOverdueParm")
	@ResponseBody
	public ParmResponse deleteByDate(Date date){
		int num = sendParmService.deleteByDate(date);
		return ResponseUtils.getBeanResponse(num, "成功删除数量",request);
	}

	@RequestMapping("/addParm")
	@ResponseBody
	public ParmResponse addParm(SendMessageParm parm){
		if (parm != null && parm.getOpenId() != null) {
			parm.setCreateDate(new Date());
			boolean done = sendParmService.add(parm);
			return ResponseUtils.getBooleanResponse(done, "",request);
		}
		return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "",request);
	}
}
