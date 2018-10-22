package com.zhiyi.medicinebox.api.application.controller.base;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.business.service.medicine.search.MedicineResp;
import com.zhiyi.medicinebox.api.business.service.medicine.search.MedicineSearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

	@Resource
	private MedicineSearchService searchService;

	@RequestMapping("/findById")
	@ResponseBody
	public MedicineResp findById(int medId) {

		MedicineResp resp = new MedicineResp();
		if (medId == 0){
			resp.setResultCode(BaseResponse.FAILED);
			resp.setResultMsg("参数 用户信息为空");
			return resp;
		}
		return searchService.searchByMedId(medId);
	}
}
