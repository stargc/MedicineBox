package com.zhiyi.medicinebox.filter;

import com.alibaba.fastjson.JSONObject;
import com.zhiyi.medicinebox.constant.ResultCode;
import com.zhiyi.medicinebox.parm.response.ParmResponse;
import com.zhiyi.medicinebox.util.ResponseUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class LogRequestFilter implements Filter{

	private final Logger logger = LogManager.getLogger(this.getClass().getName());

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		String uuid = UUID.randomUUID().toString();
	    request.setAttribute("UUID", uuid);
		logger.info("【" + uuid + "】 --- " +
				"请求地址：" + request.getRequestURL() + " --- " + 
				"请求参数：" + request.getQueryString());
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
