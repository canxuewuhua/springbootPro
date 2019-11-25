package com.example.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 日志拦截器，打印出该请求所有方法链的requestId
 */
@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    private final static String REQUEST_ID = "requestId";

    public static String getRequestId(HttpServletRequest request) {
        String requestId;
        String parameterRequestId = request.getParameter(REQUEST_ID);
        String headerRequestId = request.getHeader(REQUEST_ID);

        if (StringUtils.isEmpty(parameterRequestId) &&  StringUtils.isEmpty(headerRequestId)) {
            requestId = UUID.randomUUID().toString();
        } else {
            requestId = StringUtils.isEmpty(headerRequestId) ? parameterRequestId : headerRequestId;
        }

        return requestId;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestId = getRequestId(httpServletRequest);
        MDC.put(REQUEST_ID, requestId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        MDC.remove(REQUEST_ID);
    }
}
