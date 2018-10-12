package com.zhiyi.medicinebox.api.application.controller.sendmessage;

import com.zhiyi.medicinebox.api.business.common.constant.ResultCode;
import com.zhiyi.medicinebox.api.business.common.vo.ParmResponse;
import com.zhiyi.medicinebox.api.business.service.sendmsg.SendMessageParmService;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.SendmessageParm;
import com.zhiyi.medicinebox.api.infrastructure.util.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
		return ResponseUtils.getBeanResponse(num, "成功删除数量" + num);
	}

	@RequestMapping("/addParm")
	@ResponseBody
	public ParmResponse addParm(SendmessageParm parm){
		if (parm != null && !StringUtils.isEmpty(parm.getOpenId())) {
			boolean done = sendParmService.add(parm);
			return ResponseUtils.getBooleanResponse(done, "");
		}
		return ResponseUtils.getErrorResponse(ResultCode.RESULT_PARM_ERROR, "参数错误");
	}
}
