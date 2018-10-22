package com.zhiyi.medicinebox.api.application.controller.wexin;

import com.alibaba.fastjson.JSONObject;
import com.zhiyi.medicinebox.api.business.common.vo.ParmResponse;
import com.zhiyi.medicinebox.api.business.strategy.WXStrategy;
import com.zhiyi.medicinebox.api.infrastructure.util.ResponseUtils;
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
	private WXStrategy wxStrategy;

	/***
	 * 获取微信的 openId
	 * @return
	 */
	@RequestMapping("/queryOpenId")
	@ResponseBody
	public ParmResponse getWinXinOpenId(String loginCode){
		String result = wxStrategy.getWinXinOpenId(loginCode);
		log.info( "获取微信用户openId结果：" + result);
		Object data = JSONObject.parse(result);

		return ResponseUtils.getBeanResponse(data, "");
	}


	/***
	 * 获取微信的 openId
	 * @return
	 */
	@RequestMapping("/queryToken")
	@ResponseBody
	public ParmResponse getWinXinToken(){
		String result =wxStrategy.getWinXinToken();
		log.info( "获取微信用户token结果：" + result);
		Object data = JSONObject.parse(result);

		return ResponseUtils.getBeanResponse(data, "");
	}

	/***
	 * 通知维系发送用户提醒
	 * @return
	 */
	@RequestMapping("/sendEatMedicinePush")
	@ResponseBody
	public ParmResponse sendEatMedicinePush(String access_token, String openId, String page, String formId, String medicineName, Date alarmTime, String dosage){

		Boolean done = wxStrategy.sendEatMedicinePush(access_token, openId, page, formId, medicineName, alarmTime, dosage);
		return ResponseUtils.getBooleanResponse(done,"");
	}
}
