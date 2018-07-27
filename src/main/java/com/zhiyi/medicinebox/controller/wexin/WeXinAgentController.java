package com.zhiyi.medicinebox.controller.wexin;

import com.alibaba.fastjson.JSONObject;
import com.zhiyi.medicinebox.parm.request.WXSendMegRequest;
import com.zhiyi.medicinebox.parm.request.WXSendMegRequest.EatMedTemplate;
import com.zhiyi.medicinebox.parm.request.WXSendMegRequest.MsgTemplate;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.strategy.WXStrategy;
import com.zhiyi.medicinebox.util.ResponseUtils;
import com.zhiyi.medicinebox.util.http.HTTPUtils;
import com.zhiyi.medicinebox.util.tools.ConfigUtil;
import com.zhiyi.medicinebox.util.tools.DateUtil;
import com.zhiyi.medicinebox.util.tools.JSONUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
		Object data =  JSONUtils.String2Object(result);

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
		Object data =  JSONUtils.String2Object(result);

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
