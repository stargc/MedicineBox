package com.zhiyi.medicinebox.controller.base;

import com.zhiyi.medicinebox.entity.po.base.StatusMed;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.service.base.StatusMedService;
import com.zhiyi.medicinebox.util.ResponseUtils;
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
