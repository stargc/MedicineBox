package com.zhiyi.medicinebox.util.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONUtils {

	/**
	 * 将JavaBean序列化为JSON文本
	 * 
	 * @param bean
	 * @return
	 */
	public static String beanToJsonString(Object bean) {
		return JSONObject.toJSONString(bean,
				SerializerFeature.DisableCircularReferenceDetect);
	}

	/**
	 * 将JavaBean转换为JSONObject或者JSONArray
	 * 
	 * @param bean
	 * @return
	 */
	public static Object beanToJson(Object bean) {
		return JSONObject.toJSON(bean);
	}

	/**
	 * 将javabean对象转换为map
	 * 
	 * @param bean
	 * @return map<String, object>
	 */
	public static Map<String, Object> beanToMap(Object bean) {
		return JSONObject.parseObject(
				JSONObject.toJSONString(JSONObject.toJSON(bean)),
				new TypeReference<Map<String, Object>>() {
				});
	}
	
	public static int getInt(String json, String key){
		if (json == null) {
			return -1;
		}
		JSONObject jObject = (JSONObject) JSONObject.parse(json);
		return jObject.getIntValue(key);
	}
	
	public static String getString(String json, String key){
		if (json == null) {
			return "";
		}
		JSONObject jObject = (JSONObject) JSONObject.parse(json);
		return jObject.getString(key);
	}

	public static Object getDataToObject(String result, Class<?> c) {
		if (result == null) {
			return null;
		}
		JSONObject json = (JSONObject) JSONObject.parse(result);
		return JSONObject.parseObject(json.getString("data"), c);
	}
	
	public static Long getLong(String json, String key){
		if (json == null) {
			return -1L;
		}
		JSONObject jObject = (JSONObject) JSONObject.parse(json);
		return jObject.getLong(key);
	}

	/**
	 * 获取返回值中的data中的对象数组
	 * 
	 * @param result
	 * @return
	 */
	public static List<?> getDataList(String result, Class<?> c, String key) {
		if (result == null) {
			return null;
		}
		JSONObject json = (JSONObject) JSONObject.parse(result);
		return JSONArray.parseArray(json.getString(key), c);
	}
	
	public static Object String2Object(String text){
		return JSONObject.parse(text);
	}

}
