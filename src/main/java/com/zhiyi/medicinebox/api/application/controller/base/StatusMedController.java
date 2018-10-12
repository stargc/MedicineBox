package com.zhiyi.medicinebox.api.application.controller.base;

import com.zhiyi.medicinebox.api.business.common.vo.ParmResponse;
import com.zhiyi.medicinebox.api.business.service.base.StatusMedService;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.StatusMed;
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
@RequestMapping("/StatusMed")
public class StatusMedController {

	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	@Autowired
	private HttpServletRequest request;

	@Resource
	private StatusMedService service;
	
	@RequestMapping("/findById")
	@ResponseBody
	public ParmResponse findUserByOpenId(int id){
		StatusMed status = service.findById(id);
		return ResponseUtils.getBeanResponse(status, StatusMed.class.toString());
	}
}
