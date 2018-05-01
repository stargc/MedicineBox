package com.zhiyi.medicinebox.controller.wexin;

import com.alibaba.fastjson.JSONObject;
import com.zhiyi.medicinebox.parm.request.WXSendMegRequest;
import com.zhiyi.medicinebox.parm.request.WXSendMegRequest.EatMedTemplate;
import com.zhiyi.medicinebox.parm.request.WXSendMegRequest.MsgTemplate;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
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

	/***
	 * 获取微信的 openId
	 * @return
	 */
	@RequestMapping("/queryOpenId")
	@ResponseBody
	public ParmResponse getWinXinOpenId(String loginCode){
		String appid = ConfigUtil.getValue("wx_medbox_appid");
		String secret = ConfigUtil.getValue("wx_medbox_secret");
		
		Map<String, Object> parms = new HashMap<>();
		parms.put("appid", appid);
		parms.put("secret", secret);
		parms.put("js_code", loginCode);
		parms.put("grant_type", "authorization_code");
		String url = ConfigUtil.getValue("wx_openid_server");
		
		String result = HTTPUtils.sendGetRequest(url,parms);
		logger.info( "获取微信用户openId结果：" + result);
		Object data =  JSONUtils.String2Object(result);
		
		return ResponseUtils.getBeanResponse(data, "",request);
	}
	

	/***
	 * 获取微信的 openId
	 * @return
	 */
	@RequestMapping("/queryToken")
	@ResponseBody
	public ParmResponse getWinXinToken(){
		String appid = ConfigUtil.getValue("wx_medbox_appid");
		String secret = ConfigUtil.getValue("wx_medbox_secret");
		
		Map<String, Object> parms = new HashMap<>();
		parms.put("appid", appid);
		parms.put("secret", secret);
		parms.put("grant_type", "client_credential");
		String url = ConfigUtil.getValue("wx_token_server");
		
		String result = HTTPUtils.sendGetRequest(url,parms);
		logger.info( "获取微信用户token结果：" + result);
		Object data =  JSONUtils.String2Object(result);
		
		return ResponseUtils.getBeanResponse(data, "",request);
	}
	
	/***
	 * 通知维系发送用户提醒 
	 * @return
	 */
	@RequestMapping("/sendEatMedicinePush")
	@ResponseBody
	public ParmResponse sendEatMedicinePush(String access_token,String openId,String page,String formId,String medicineName,Date alarmTime, String dosage){
		String template = ConfigUtil.getValue("wx_template_sned_medicine_message");
		
		EatMedTemplate data = new EatMedTemplate();
		
		MsgTemplate keyword1 = new MsgTemplate();
		keyword1.setColor("#173177");
		keyword1.setValue(medicineName);
		data.setKeyword1(keyword1);

		MsgTemplate keyword2 = new MsgTemplate();
		keyword2.setColor("#173177");
		keyword2.setValue(DateUtil.Date2TimeString(alarmTime));
		data.setKeyword2(keyword2);

		MsgTemplate keyword3 = new MsgTemplate();
		keyword3.setColor("#173177");
		keyword3.setValue(dosage);
		data.setKeyword3(keyword3);
		
		WXSendMegRequest parms = new WXSendMegRequest();
		parms.setData(data);
		parms.setForm_id(formId);
		parms.setPage(page);
		parms.setTemplate_id(template);
		parms.setTouser(openId);
		String url = new StringBuffer(ConfigUtil.getValue("wx_send_message_server")).append(access_token).toString();
		
		String result = HTTPUtils.httpConnectionPost(url,JSONObject.toJSONString(parms));
		logger.info( "微信发送用户提醒结果：" + result);
		Object resultObject =  JSONUtils.String2Object(result);
		
		return ResponseUtils.getBeanResponse(resultObject, "",request);
	}
}
