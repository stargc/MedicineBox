package com.zhiyi.medicinebox.api.application.controller.base;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.business.service.medicine.medstatus.MedStatusSearchResp;
import com.zhiyi.medicinebox.api.business.service.medicine.medstatus.MedStatusSearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/StatusMed")
public class StatusMedController {

	@Resource
	private MedStatusSearchService medStatusSearchService;


	@RequestMapping("/findById")
	@ResponseBody
	public MedStatusSearchResp findUserByOpenId(int id){

		MedStatusSearchResp resp = new MedStatusSearchResp();
		if (id == 0){
			resp.setResultCode(BaseResponse.FAILED);
			resp.setResultMsg("参数 用户信息为空");
			return resp;
		}
		return medStatusSearchService.searchById(id);
	}
}
