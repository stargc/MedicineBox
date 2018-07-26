package com.zhiyi.medicinebox.util;

import javax.servlet.http.HttpServletRequest;

import com.zhiyi.medicinebox.constant.ResultCode;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author guanchen
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
	public static ParmResponse getListResponse(Object[] beans, String msg) {
		ParmResponse response = new ParmResponse();
		if (beans == null || beans.length == 0) {
			response.setCode(ResultCode.RESULT_NULL);
			response.setMsg("数据为空");
		} else {
			response.setCode(ResultCode.RESULT_NORMAL);
			response.setMsg(msg);
			response.setData(beans);
		}

		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
		if (request != null){
			logger.info( "【" + request.getAttribute("UUID").toString() + "】 --- " +
					"返回结果：" + response.toJson());
		} else {
			logger.info( "返回结果：" + response.toJson());
		}
		return response;
	}

	/***
	 * 
	 * @param bean
	 * @param msg 对结果信息的描述
	 * @return
	 */
	public static ParmResponse getBeanResponse(Object bean, String msg) {
		ParmResponse response = new ParmResponse();
		if (bean == null) {
			response.setCode(ResultCode.RESULT_NULL);
			response.setMsg("数据为空");
		} else {
			response.setCode(ResultCode.RESULT_NORMAL);
			response.setMsg(msg);
			response.setData(bean);
		}

		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
		if (request != null){
			logger.info( "【" + request.getAttribute("UUID").toString() + "】 --- " +
					"返回结果：" + response.toJson());
		} else {
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
	public static ParmResponse getErrorResponse(String code, String msg) {
		ParmResponse response = new ParmResponse();
		response.setCode(code);
		response.setMsg(msg);

		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
		if (request != null){
			logger.info( "【" + request.getAttribute("UUID").toString() + "】 --- " +
					"返回结果：" + response.toJson());
		} else {
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
	public static ParmResponse getBooleanResponse(boolean single, String msg) {
		ParmResponse response = new ParmResponse();
		if (!single){
			response.setCode(ResultCode.RESULT_FAIL);
		} else {
			response.setCode(ResultCode.RESULT_NORMAL);
		}
		response.setMsg(msg);
		response.setData(single);

		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
		if (request != null){
			logger.info( "【" + request.getAttribute("UUID").toString() + "】 --- " +
					"返回结果：" + response.toJson());
		} else {
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
