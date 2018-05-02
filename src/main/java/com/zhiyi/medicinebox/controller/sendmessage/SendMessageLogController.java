package com.zhiyi.medicinebox.controller.sendmessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiyi.medicinebox.constant.ResultCode;
import com.zhiyi.medicinebox.entity.SendMessageLog;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.service.SendMessageLogService;
import com.zhiyi.medicinebox.util.ResponseUtils;

@Controller
@RequestMapping("/SendMessageLog")
public class SendMessageLogController {

	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired  
	private HttpServletRequest request;

	@Resource
	private SendMessageLogService sendService;
	
	@RequestMapping("/add")
	public ParmResponse addSendMessageLog(SendMessageLog log){
		if (log != null && log.getUserId() != 0) {
			boolean done = sendService.add(log);
			return ResponseUtils.getBooleanResponse(done, "",request);
		}
		return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "参数错误",request);
	}
}
