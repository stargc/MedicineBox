package com.zhiyi.medicinebox.controller.record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyi.medicinebox.constant.ResultCode;
import com.zhiyi.medicinebox.entity.ViewRecord;
import com.zhiyi.medicinebox.entity.ViewRecord_show;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.service.ViewRecordService;
import com.zhiyi.medicinebox.util.ResponseUtils;

@Controller
@RequestMapping("/viewrecord")
public class ViewRecordController {

	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired  
	private HttpServletRequest request;

	@Resource
	private ViewRecordService viewRecordService;
	
	@RequestMapping("/findAlarmByUser")
	@ResponseBody
	public ParmResponse findAlarm(int userId) {
		List<ViewRecord_show> result = new ArrayList<>();
		List<ViewRecord> list = viewRecordService.findByUserId(userId, new Date());
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				result.add(new ViewRecord_show(list.get(i)));
			}
			return ResponseUtils.getListResponse(result.toArray(), ViewRecord.class.toString(),request);
		}
		return ResponseUtils.getErrorResponse(ResultCode.RESULT_NULL, "数据为空",request);
	}
}
