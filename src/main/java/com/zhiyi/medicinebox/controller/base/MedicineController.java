package com.zhiyi.medicinebox.controller.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyi.medicinebox.entity.Medicine;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.service.MedicineService;
import com.zhiyi.medicinebox.util.ResponseUtils;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired  
	private HttpServletRequest request;

	@Resource
	private MedicineService medicineService;

	@RequestMapping("/findById")
	@ResponseBody
	public ParmResponse findById(int medId) {
		Medicine medicine = medicineService.findById(medId);
		return ResponseUtils.getBeanResponse(medicine, "",request);
	}
}
