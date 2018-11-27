package com.zhiyi.medicinebox.api.application.controller;

import com.zhiyi.medicinebox.api.business.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private TestService service;

	@RequestMapping("/test")
	@ResponseBody
	public String test(String test) {
		log.info("get request " + test);
		for (int i = 0; i < 6; i++) {
			service.test(String.valueOf(i));
		}
		return "success";
	}

}
