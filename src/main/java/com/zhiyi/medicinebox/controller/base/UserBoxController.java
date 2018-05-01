package com.zhiyi.medicinebox.controller.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiyi.medicinebox.service.UserBoxService;

@Controller
@RequestMapping("/userbox")
public class UserBoxController {

	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired  
	private HttpServletRequest request;

	@Resource
	private UserBoxService userBoxService;
}
