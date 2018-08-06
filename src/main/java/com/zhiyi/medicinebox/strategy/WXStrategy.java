package com.zhiyi.medicinebox.strategy;

import com.alibaba.fastjson.JSONObject;
import com.zhiyi.medicinebox.parm.request.WXSendMegRequest;
import com.zhiyi.medicinebox.parm.request.WXSendMegRequest.EatMedTemplate;
import com.zhiyi.medicinebox.parm.request.WXSendMegRequest.MsgTemplate;
import com.zhiyi.medicinebox.util.http.HTTPUtils;
import com.zhiyi.medicinebox.util.tools.ConfigUtil;
import com.zhiyi.medicinebox.util.tools.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guanchen
 * @version $Id AlarmStrategy.java, v 0.1 2018-07-19 17:49 star Exp $$
 */
@Service
public class WXStrategy {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    /***
     * 获取用户 OpenId.
     * @param loginCode 小程序调用wx.login() 获取 临时登录凭证code
     * @return {"openid": "OPENID","session_key": "SESSIONKEY"}

     */
    public String getWinXinOpenId(String loginCode) {
        String appid = ConfigUtil.getValue("wx_medbox_appid");
        String secret = ConfigUtil.getValue("wx_medbox_secret");

        Map<String, String> parms = new HashMap<>();
        parms.put("appid", appid);
        parms.put("secret", secret);
        parms.put("js_code", loginCode);
        parms.put("grant_type", "authorization_code");
        String url = ConfigUtil.getValue("wx_openid_server");

        String result = HTTPUtils.sendGet(url, parms);
        return result;
    }

    /***
     *  获取微信 access_token.
     *  access_token 是全局唯一接口调用凭据
     * @return {"access_token": "ACCESS_TOKEN", "expires_in": 7200}  < access_token:获取到的凭证; expires_in:凭证有效时间，单位：秒 >
     */
    public String getWinXinToken() {
        String appid = ConfigUtil.getValue("wx_medbox_appid");
        String secret = ConfigUtil.getValue("wx_medbox_secret");

        Map<String, String> parms = new HashMap<>();
        parms.put("appid", appid);
        parms.put("secret", secret);
        parms.put("grant_type", "client_credential");
        String url = ConfigUtil.getValue("wx_token_server");

        String result = HTTPUtils.sendGet(url, parms);
        return result;
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
        String template = ConfigUtil.getValue("wx_template_sned_medicine_message");

        EatMedTemplate data = new EatMedTemplate();

        WXSendMegRequest.MsgTemplate keyword1 = new MsgTemplate();
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

        String result = HTTPUtils.httpConnectionPost(url, JSONObject.toJSONString(parms));
        logger.info("微信发送用户提醒结果：" + result);
        Map<String, String> resultMap = JSONObject.parseObject(result, Map.class);
        if (resultMap == null || StringUtils.isEmpty(String.valueOf(resultMap.get("errcode")))) {
            return false;
        }
        return "0".equals(String.valueOf(resultMap.get("errcode")));
    }
}
