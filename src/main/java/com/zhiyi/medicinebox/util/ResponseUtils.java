package com.zhiyi.medicinebox.util;

import javax.servlet.http.HttpServletRequest;

import com.zhiyi.medicinebox.constant.ResultCode;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 对services程序返回的json string格式数据进行解析 返回值格式是确定的: { code:0|1,//0:数据正常；1:没有数据 msg
 * :信息,//class data:{} //可能是对象,也可能是数组,也可能是null }
 * 
 * @author 赵锋
 *
 */
public class ResponseUtils {

	private static final Logger logger = LogManager.getLogger(ResponseUtils.class.getName());

	/***
	 * 
	 * @param beans 
	 * @param msg 对结果信息的描述
	 * @return
	 */
	public static ParmResponse getListResponse(Object[] beans, String msg, HttpServletRequest request) {
		ParmResponse response = new ParmResponse();
		if (beans == null || beans.length == 0) {
			response.setCode(ResultCode.RESULT_NULL);
			response.setMsg("数据为空");
		} else {
			response.setCode(ResultCode.RESULT_NORMAL);
			response.setMsg(msg);
			response.setData(beans);
		}
		try {
			logger.info( "【" + request.getAttribute("UUID").toString() + "】 --- " +
					"返回结果：" + response.toJson());
		} catch (NullPointerException e) {
			logger.info("返回结果：" + response.toJson());
		}
		return response;
	}
//	public static String getResponse(Object[] beans, Object msg) {
//	Map<String, Object> map = new HashMap<String, Object>();
//	if (beans == null || beans.length == 0) {
//		map.put("code", 1);
//		map.put("data", null);
//		map.put("msg", "数据为空");
//	} else {
//		map.put("code", 0);
//		// List<String> result = new ArrayList<String>();
//		// for (int i = 0; i < beans.length; i++) {
//		// String temp = JSONUtils.beanToJson(beans[i]).toString();
//		// result.add(temp);
//		// }
//		map.put("data", JSONUtils.beanToJson(beans));
//		map.put("msg", msg);
//	}
//	return JSONUtils.beanToJsonString(map);
//}
	

	/***
	 * 
	 * @param bean
	 * @param msg 对结果信息的描述
	 * @return
	 */
	public static ParmResponse getBeanResponse(Object bean, String msg, HttpServletRequest request) {
		ParmResponse response = new ParmResponse();
		if (bean == null) {
			response.setCode(ResultCode.RESULT_NULL);
			response.setMsg("数据为空");
		} else {
			response.setCode(ResultCode.RESULT_NORMAL);
			response.setMsg(msg);
			response.setData(bean);
		}
		try {
			logger.info( "【" + request.getAttribute("UUID").toString() + "】 --- " +
					"返回结果：" + response.toJson());
		} catch (NullPointerException e) {
			logger.info( "返回结果：" + response.toJson());
		}
		return response;
	}

	/***
	 * 
	 * @param code 0：成功	1：数据为空	  2:操作失败	 3：参数错误	4：其他错误
	 * @param msg 对code信息的描述
	 * @return
	 */
	public static ParmResponse getErrorResponse(String code, String msg, HttpServletRequest request) {
		ParmResponse response = new ParmResponse();
		response.setCode(code);
		response.setMsg(msg);
		try {
			logger.info( "【" + request.getAttribute("UUID").toString() + "】 --- " +
					"返回结果：" + response.toJson());
		} catch (NullPointerException e) {
			logger.info( "返回结果：" + response.toJson());
		}
		return response;
	}
//	public static String getErrorResponse(int code, Object msg) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("data", null);
//		map.put("code", code);
//		map.put("msg", msg);
//		return JSONUtils.beanToJsonString(map);
//	}

	/****
	 * 
	 * @param single 操作是否成功
	 * @param msg
	 * @return
	 */
	public static ParmResponse getBooleanResponse(boolean single, String msg, HttpServletRequest request) {
		ParmResponse response = new ParmResponse();
		if (!single){
			response.setCode(ResultCode.RESULT_FAIL);
		} else {
			response.setCode(ResultCode.RESULT_NORMAL);
		}
		response.setMsg(msg);
		response.setData(single);
		try {
			logger.info( "【" + request.getAttribute("UUID").toString() + "】 --- " +
					"返回结果：" + response.toJson());
		} catch (NullPointerException e) {
			logger.info( "返回结果：" + response.toJson());
		}
		return response;
	}
//	public static String getBooleanResponse(boolean single, Object msg) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		if (!single)
//			map.put("code", 2);
//		else
//			map.put("code", 0);
//		map.put("msg", msg);
//		map.put("data", single);
//		return JSONUtils.beanToJsonString(map);
//	}
}
