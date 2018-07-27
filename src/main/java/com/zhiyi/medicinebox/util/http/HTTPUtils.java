package com.zhiyi.medicinebox.util.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HTTPUtils {

	private static final Logger logger = LogManager.getLogger(HTTPUtils.class.getName());

	/**
	 * httpClient的方式上传文件
	 * 
	 * @param path
	 * @param file
	 * @param map
	 * @return
	 */
	public static boolean uploadFile(String path, File file, Map<String, String> map) {
		PostMethod filePost = new PostMethod(path);
		List<Part> list = new ArrayList<>();
		try {
			list.add(new FilePart("file", file));
			if (map != null) {
				for (Entry<String, String> entry : map.entrySet()) {
					StringPart stringPart = new StringPart(entry.getKey(), entry.getValue());
					list.add(stringPart);
				}
			}
			Part[] parts = new Part[list.size()];
			for (int i = 0; i < list.size(); i++) {
				parts[i] = list.get(i);
			}
			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			int status = client.executeMethod(filePost);
			return status == HttpStatus.SC_OK;
		} catch (Exception ex) {
			logger.error(ExceptionUtils.getStackTrace(ex));
		}
		return false;
	}
	/**
	 * httpClient的方式上传文件
	 * 
	 * @param path
	 * @param file
	 * @param map
	 * @return
	 */
	public static String batchuploadexcelFile(String path, File file, Map<String, String> map) { 
		String str=null;
		PostMethod filePost = new PostMethod(path);
		List<Part> list = new ArrayList<>();
		try {
			list.add(new FilePart("file", file));
			if (map != null) {
				for (Entry<String, String> entry : map.entrySet()) {
					StringPart stringPart = new StringPart(entry.getKey(), entry.getValue());
					list.add(stringPart);
				}
			}
			Part[] parts = new Part[list.size()];
			for (int i = 0; i < list.size(); i++) {
				parts[i] = list.get(i);
			}
			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
			int status = client.executeMethod(filePost);
			if(status == HttpStatus.SC_OK){
				str = filePost.getResponseBodyAsString();
			}else{
				Map<String, Object> mapnode = new HashMap<String, Object>();
				mapnode.put("code", 3);
				mapnode.put("msg", "连接超时");
				mapnode.put("data", "");
				JSONObject json = new JSONObject();
				json.putAll(mapnode);
				str = json.toJSONString();
			}
		} catch (Exception ex) {
			logger.error(ExceptionUtils.getStackTrace(ex));
		}
		return str;
	}

    /**
	 * 使用HttpURLConnection 发送HTTP post
	 * 
	 * @param serverUrl
	 * @param data  json格式
	 */
	public static String httpConnectionPost(String serverUrl, String data) {
		logger.info( "发送HTTP请求， url:" + serverUrl + " 参数： " + data);
		// 服务地址
		URL url;
		try {
			url = new URL(serverUrl);
		} catch (MalformedURLException e1) {
//			err(e1);
			return getReturnMessage("500", "fail");
		}
		// 设定连接的相关参数
		HttpURLConnection connection = null;
		String strResponse = "";
		BufferedReader reader = null;
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(250 * 1000);// 30秒
			connection.setReadTimeout(250 * 1000);// 30秒
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Connection", "close");

			// 输出
			OutputStreamWriter output = new OutputStreamWriter(
					connection.getOutputStream(), "utf-8");
			output.write(data);
			output.flush();
			output.close();

			// 获取服务端的反馈
			String strLine = "";
			InputStream in = connection.getInputStream();

			reader = new BufferedReader(new InputStreamReader(in));

			while ((strLine = reader.readLine()) != null) {
				strResponse += strLine + "\n";
			}
		} catch (IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
			try {
				connection.disconnect();
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
		return strResponse;
	}

    private static String getReturnMessage(String status, String info) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("{ ");
		sbf.append("\"code\":\"" + status + "\",");
		sbf.append("\"meg\":\"" + info + "\",");
		sbf.append("\"status\":\"99\"");
		sbf.append(" }");
		return sbf.toString();
	}
	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param parm
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, Map<String, String> parm) {
		String result = "";
		BufferedReader in = null;
		try {
			StringBuffer parmString = new StringBuffer();
			if (parm != null) {
				for (Map.Entry<String, String> entry : parm.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					parmString = parmString.append("&").
							append(key).append("=").append(value);
				}
			}
			StringBuffer urlNameString = new StringBuffer(url).append("?").append(parmString.substring(1,parmString.length()));
			URL realUrl = new URL(urlNameString.toString());
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "close");
//            connection.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");


			connection.setRequestProperty("Content-Type", "application/json");
//			connection.setRequestProperty("Connection", "close");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

}
