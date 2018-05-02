package com.zhiyi.medicinebox.filter;
import org.apache.commons.httpclient.util.ExceptionUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
public class CharacterEncodingFilter implements Filter {

	private final Logger logger = LogManager.getLogger(this.getClass().getName());

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		/**
		 * 此方法体对GET 和 POST方法均可
		 */
		if (request.getMethod().compareToIgnoreCase("post") >= 0) {
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		} else {
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
//			Iterator iter = request.getParameterMap().values().iterator();
//			while (iter.hasNext()) {
//				String[] parames = (String[]) iter.next();
//				for (int i = 0; i < parames.length; i++) {
//					try {
//						parames[i] = new String(parames[i].getBytes("iso8859-1"), "UTF-8");// 此处GBK与页面编码一样
//					} catch (UnsupportedEncodingException e) {
//						logger.error(ExceptionUtils.getStackTrace(e));
//					}
//				}
//			}
		}
	    chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
