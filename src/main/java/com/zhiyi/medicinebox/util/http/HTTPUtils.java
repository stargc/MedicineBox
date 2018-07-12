package com.zhiyi.medicinebox.util.http;

import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.zhiyi.medicinebox.util.tools.StringUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;

public class HTTPUtils {

	private static final Logger logger = LogManager.getLogger(HTTPUtils.class.getName());
	private static ClientConfig config = new DefaultClientConfig();
	private static Client client = Client.create(config);

	/**
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException  
	 */
	private static MultivaluedMapImpl parseMap(Map<String, Object> map) throws UnsupportedEncodingException {
		MultivaluedMapImpl params = new MultivaluedMapImpl();

		Set<Entry<String, Object>> entrySet = map.entrySet();
		Iterator<Entry<String, Object>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			Object val=entry.getValue();
			if(val!=null){
//				val=URLEncoder.encode(val.toString(), "utf-8");
				try {
					val=((String)val).replace("+", "%2B");
				} catch (Exception e) {
					logger.error(ExceptionUtils.getStackTrace(e));
				}
//				val=((String)val).replace("%3D", "%3D");
//				val=((String)val).replace("%20", " ");
//				val=((String)val).replace("%3A", ":");
			}
			params.add(entry.getKey(), val);
		}
		return params;
	}

  /**
 * @param path
 * @return  
 */
public static String sendGetRequest(String path) {
    String str = null;
    try {
      URI uri = new URI(path);
      WebResource resource = client.resource(uri);
      str = resource.get(String.class);
      return str;
    } catch (URISyntaxException e) {
		logger.error(ExceptionUtils.getStackTrace(e));
    }
    return str;
  }

	/**
	 * @param path
	 * @param map
	 * @return  
	 */
	public static String sendGetRequest(String path, Map<String, Object> map) {
		String str = null;
		try {
			MultivaluedMapImpl params = parseMap(map);
			URI uri = new URI(path);
			WebResource resource = client.resource(uri);
			str = resource.queryParams(params).get(String.class);
			return str;
		} catch (URISyntaxException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}catch (UnsupportedEncodingException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return str;
	}

	/**
	 * @param path
	 *            访问添加的路径
	 * @param map
	 *            添加的条件
	 * @return
	 */
	public static String sendPostRequest(String path, Map<String, Object> map) {
		String str = null;
		try {
			MultivaluedMapImpl params = parseMap(map);
			URI uri = new URI(path);
			WebResource resource = client.resource(uri);
			str = resource.queryParams(params).post(String.class);
			return str;
		} catch (URISyntaxException | UnsupportedEncodingException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return str;
	}

	/**
	 * 
	 * @param path
	 *            访问删除的路径
	 * @return
	 */
	public static String sendDeleteRequest(String path) {
		String str = null;
		try {
			URI uri = new URI(path);
			WebResource resource = client.resource(uri);
			str = resource.delete(String.class);
		} catch (URISyntaxException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return str;
	}

	/**
	 * 
	 * @param path
	 * @param entity
	 * @return
	 */
	public static String sendPutRequest(String path, Object entity) {
		String str = null;
		try {
			URI uri = new URI(path);
			WebResource resource = client.resource(uri);
			str = resource.entity(entity).put(String.class);
			return str;
		} catch (URISyntaxException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return str;
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static String sendGetRequest(String path, Object entity) {
		String str = null;
		try {
			URI uri = new URI(path);
			WebResource resource = client.resource(uri);
			str = resource.entity(entity).get(String.class);
			return str;
		} catch (URISyntaxException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return str;
	}
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
	 * httpClient的方式下载文件
	 * @param path
	 * @param map
	 * @param savePath 下载文件 保存地址
	 * @return
	 */
	public static boolean downloadFile(String path, Map<String, String> map,String savePath) {
		HttpClient client = new HttpClient();
		String url = StringUtil.contactStr(path);
		if (map != null) {
			url=StringUtil.contactStr(url,"?");
			for (Map.Entry<String, String> entry : map.entrySet()) {
				url=StringUtil.contactStr(url,entry.getKey(),"=",entry.getValue(),"&");
			}
			if(url.endsWith("&")) {
				url = url.substring(0, url.length() - 1);
			}
		}
		GetMethod httpGet = new GetMethod(url);
		try {
			client.executeMethod(httpGet);
			InputStream in = httpGet.getResponseBodyAsStream();
			File file = new File(savePath);
			if (!file.exists()) {
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdir();
				}
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file);
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return false;
		} finally {
			httpGet.releaseConnection();
		}
		return true;
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
			return getReturnMessage("fail", "fail");
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
    
}