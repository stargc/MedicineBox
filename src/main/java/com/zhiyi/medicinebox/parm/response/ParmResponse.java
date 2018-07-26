package com.zhiyi.medicinebox.parm.response;

import com.zhiyi.medicinebox.util.tools.JSONUtils;

/**
 * 返回参数总接口
 *
 */
public class ParmResponse {

	/** 服务器返回结果 */
	private String code;

	/** 返回消息 */
	private String msg;
	
	private Object data;
	
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String resultCode) {
		this.code = resultCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String resultMsg) {
		this.msg = resultMsg;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String toJson() {
		return JSONUtils.beanToJsonString(this);
	}
	
}
