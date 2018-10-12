package com.zhiyi.medicinebox.api.application.controller.base;

import com.zhiyi.medicinebox.api.business.common.vo.ParmResponse;
import com.zhiyi.medicinebox.api.business.service.base.MedicineService;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Medicine;
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
		return ResponseUtils.getBeanResponse(medicine, "");
	}
}
