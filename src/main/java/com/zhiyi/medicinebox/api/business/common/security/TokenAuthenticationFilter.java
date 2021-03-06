package com.zhiyi.medicinebox.api.business.common.security;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.infrastructure.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

@Component
public class TokenAuthenticationFilter extends GenericFilterBean {

    public static final Logger LOG = LoggerFactory.getLogger(TokenAuthenticationFilter.class);


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        response.setCharacterEncoding("utf-8");

        //获得认证token
        String authorization = httpRequest.getHeader("authorization") != null ?
                httpRequest.getHeader("authorization") : httpRequest.getParameter("authorization");
        if (StringUtils.isEmpty(authorization)) {
            LOG.info("用户名或token不能为空");
            response.setContentType("application/json");
            response.getWriter().write(JsonUtil.beanToString(responseFailure("请求中未找到认证信息")));
            return;
        }
        LOG.info("用户登陆信息：{}", authorization);

        //检查token有效性
        String[] userAndPass = new String(Base64.getDecoder().decode(authorization.split(" ")[1])).split(":");
        if (!isValidUser(userAndPass[0], userAndPass[1])) {
            LOG.info("用户登陆已超时：username={},token={}", userAndPass[0], userAndPass[1]);
            response.setContentType("application/json");
            response.getWriter().write(JsonUtil.beanToString(responseFailure("用户登陆超时")));
            return;
        }

        //更新token时间
        refreshTokenTime(userAndPass[0], userAndPass[1]);

        chain.doFilter(request, response);
    }

    private BaseResponse responseFailure(String errorMsg) {
        BaseResponse response = new BaseResponse();
        response.setResultCode(BaseResponse.FAILED);
        response.setResultMsg(errorMsg);
        return response;
    }

    private boolean isValidUser(String userName, String token) {
        return true;
    }

    private int refreshTokenTime(String userName, String token) {
        return 1;
    }
}
