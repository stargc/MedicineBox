package com.zhiyi.medicinebox.util.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiyi.medicinebox.constant.ResultCode;
import com.zhiyi.medicinebox.util.ResponseUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public class ExceptionHandler implements HandlerExceptionResolver {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        logger.error(ExceptionUtils.getStackTrace(ex));
        ModelAndView mv = new ModelAndView();

         /*	使用response返回	*/
        response.setStatus(HttpStatus.OK.value()); //设置状态码
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        response.setCharacterEncoding("UTF-8"); //避免乱码
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            response.getWriter().write(ResponseUtils.getErrorResponse(ResultCode.RESULT_ERROR,ex.getMessage()).toJson());
        } catch (IOException e) {
            logger.error("与客户端通讯异常:"+ e.getMessage(), e);
        }

        return mv;
    }

} 