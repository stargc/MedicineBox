package com.zhiyi.medicinebox.api.application.controller.wexin;

import com.alibaba.fastjson.JSONObject;
import com.zhiyi.medicinebox.api.business.common.vo.ParmResponse;
import com.zhiyi.medicinebox.api.business.strategy.WXStrategy;
import com.zhiyi.medicinebox.api.infrastructure.util.ResponseUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/WeXinAgent")
public class WeXinAgentController {

	private final Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	private HttpServletRequest request;

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
		logger.info( "获取微信用户openId结果：" + result);
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
		logger.info( "获取微信用户token结果：" + result);
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
