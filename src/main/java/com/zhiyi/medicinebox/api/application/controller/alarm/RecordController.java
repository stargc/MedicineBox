package com.zhiyi.medicinebox.api.application.controller.alarm;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.business.service.record.RecordListResp;
import com.zhiyi.medicinebox.api.business.service.record.RecordSearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/record")
public class RecordController {

	@Resource
	private RecordSearchService searchService;
	
	@RequestMapping("/findAlarmByUser")
	@ResponseBody
	public RecordListResp findAlarm(int userId) {
		RecordListResp resp = new RecordListResp();
		if (userId == 0){
			resp.setResultCode(BaseResponse.FAILED);
			resp.setResultMsg("参数 用户信息为空");
		}
		return searchService.searchByUserId(userId);
	}
}
