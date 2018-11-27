package com.zhiyi.medicinebox.api.business.service.weixin;

import com.alibaba.fastjson.JSONObject;
import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.business.service.weixin.vo.WXOpenIdResp;
import com.zhiyi.medicinebox.api.business.service.weixin.vo.WXSendMegReq;
import com.zhiyi.medicinebox.api.business.service.weixin.vo.WXTokenResp;
import com.zhiyi.medicinebox.api.infrastructure.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guanchen
 * @version $Id AlarmStrategy.java, v 0.1 2018-07-19 17:49 star Exp $$
 */
@Service
public class WXService {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    private RestTemplate restTemplate;

    @Value("${wx_medbox_appid}")
    private String wxMedboxAppid;
    @Value("${wx_medbox_secret}")
    private String wxMedboxSecret;
    @Value("${wx_openid_server}")
    private String wxOpenidServer;

    @Value("${wx_token_server}")
    private String wxTokenServer;

    @Value("${wx_template_sned_medicine_message}")
    private String wxTemplateSnedMedicineMessage;

    @Value("${wx_send_message_server}")
    private String wxSendMessageServer;

    /***
     * 获取用户 OpenId.
     * @param loginCode 小程序调用wx.login() 获取 临时登录凭证code
     * @return {"openid": "OPENID","session_key": "SESSIONKEY"}

     */
    public WXOpenIdResp getWinXinOpenId(String loginCode) {

        Map<String, String> parms = new HashMap<>();
        parms.put("appid", wxMedboxAppid);
        parms.put("secret", wxMedboxSecret);
        parms.put("js_code", loginCode);
        parms.put("grant_type", "authorization_code");

        String result = restTemplate.getForObject(wxOpenidServer, String.class, parms);
        Map<String,String> resultMap = JSONObject.parseObject(result, Map.class);
        WXOpenIdResp resp = new WXOpenIdResp();
        if (StringUtils.isBlank(resultMap.get("openid"))){
            resp.setResultCode(BaseResponse.FAILED);
            resp.setResultMsg("获取失败");
            return resp;
        }
        resp.setResultCode(BaseResponse.SUCCESS);
        resp.setOpenid(resultMap.get("openid"));
        resp.setSessionKey(resultMap.get("session_key"));
        return resp;
    }

    /***
     *  获取微信 access_token.
     *  access_token 是全局唯一接口调用凭据
     * @return {"access_token": "ACCESS_TOKEN", "expires_in": 7200}  < access_token:获取到的凭证; expires_in:凭证有效时间，单位：秒 >
     */
    public WXTokenResp getWinXinToken() {

        Map<String, String> parms = new HashMap<>();
        parms.put("appid", wxMedboxAppid);
        parms.put("secret", wxMedboxSecret);
        parms.put("grant_type", "client_credential");

        String result = restTemplate.getForObject(wxTokenServer, String.class, parms);
        Map<String,String> resultMap = JSONObject.parseObject(result, Map.class);
        WXTokenResp resp = new WXTokenResp();
        if (StringUtils.isBlank(resultMap.get("access_token"))){
            resp.setResultCode(BaseResponse.FAILED);
            resp.setResultMsg("获取失败");
            return resp;
        }
        resp.setResultCode(BaseResponse.SUCCESS);
        resp.setAccessToken(resultMap.get("access_token"));
        resp.setExpiresIn(resultMap.get("expires_in"));
        return resp;
    }

    /***
     * 微信发送 模板消息
     * @param access_token
     * @param openId 接收者（用户）的 openid
     * @param page 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
     * @param formId 表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
     * @param medicineName 药品名称
     * @param alarmTime 吃药时间
     * @param dosage 用量
     * @return
     */
    public boolean sendEatMedicinePush(String access_token, String openId, String page, String formId, String medicineName, Date alarmTime, String dosage) {

        WXSendMegReq.EatMedTemplate data = new WXSendMegReq.EatMedTemplate();

        WXSendMegReq.MsgTemplate keyword1 = new WXSendMegReq.MsgTemplate();
        keyword1.setColor("#173177");
        keyword1.setValue(medicineName);
        data.setKeyword1(keyword1);

        WXSendMegReq.MsgTemplate keyword2 = new WXSendMegReq.MsgTemplate();
        keyword2.setColor("#173177");
        keyword2.setValue(DateUtils.Date2TimeString(alarmTime));
        data.setKeyword2(keyword2);

        WXSendMegReq.MsgTemplate keyword3 = new WXSendMegReq.MsgTemplate();
        keyword3.setColor("#173177");
        keyword3.setValue(dosage);
        data.setKeyword3(keyword3);

        WXSendMegReq parms = new WXSendMegReq();
        parms.setData(data);
        parms.setForm_id(formId);
        parms.setPage(page);
        parms.setTemplate_id(wxTemplateSnedMedicineMessage);
        parms.setTouser(openId);
        String url = new StringBuffer(wxSendMessageServer).append(access_token).toString();

        String result = restTemplate.patchForObject(url, JSONObject.toJSONString(parms), String.class);
        logger.info("微信发送用户提醒结果：" + result);
        Map<String, String> resultMap = JSONObject.parseObject(result, Map.class);
        if (resultMap == null || StringUtils.isEmpty(String.valueOf(resultMap.get("errcode")))) {
            return false;
        }
        return "0".equals(String.valueOf(resultMap.get("errcode")));
    }
}
