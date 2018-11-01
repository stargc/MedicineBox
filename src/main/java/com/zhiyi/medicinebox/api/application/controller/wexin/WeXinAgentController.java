package com.zhiyi.medicinebox.api.application.controller.wexin;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.business.service.weixin.vo.WXOpenIdResp;
import com.zhiyi.medicinebox.api.business.service.weixin.vo.WXTokenResp;
import com.zhiyi.medicinebox.api.business.service.weixin.WXService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/WeXinAgent")
@Slf4j
public class WeXinAgentController {

	@Resource
	private WXService wxService;

	/***
	 * 获取微信的 openId
	 * @return
	 */
	@RequestMapping("/queryOpenId")
	@ResponseBody
	public WXOpenIdResp getWinXinOpenId(String loginCode){
		return wxService.getWinXinOpenId(loginCode);
	}


	/***
	 * 获取微信的 openId
	 * @return
	 */
	@RequestMapping("/queryToken")
	@ResponseBody
	public WXTokenResp getWinXinToken(){
		return wxService.getWinXinToken();
	}

	/***
	 * 通知微信发送用户提醒
	 * @return
	 */
	@RequestMapping("/sendEatMedicinePush")
	@ResponseBody
	public BaseResponse sendEatMedicinePush(String access_token, String openId, String page, String formId, String medicineName, Date alarmTime, String dosage){
		BaseResponse resp = new BaseResponse();
		Boolean done = wxService.sendEatMedicinePush(access_token, openId, page, formId, medicineName, alarmTime, dosage);
		resp.setResultCode(done ? BaseResponse.SUCCESS : BaseResponse.FAILED);
		return resp;
	}
}
