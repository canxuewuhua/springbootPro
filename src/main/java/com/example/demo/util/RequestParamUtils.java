package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RequestParamUtils {

    /**
     * 键值对获取参数
     *
     * @param request
     * @return
     */
    public static Map<String, String> getParamsFromRequestNameValues(HttpServletRequest request) {

        log.debug("从键值对中获取参数");
        Map<String, String> params = new HashMap<>();
        StringBuffer sb = new StringBuffer("");
        try {
            String uri = request.getRequestURI();
            Enumeration parameterNames = request.getParameterNames();
            sb.append("uri===>>>" + uri + "\nparams:[");
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement().toString();
                String[] values = request.getParameterValues(name);
                if (null != values && values.length > 0) {
                    for (String value : values) {
                        params.put(name, value);
                        sb.append("\n" + name + "=" + value);
                    }
                }
            }
            sb.append("\n]");
        } catch (Exception e) {
            log.error("从键值对中获取参数异常", e);
        }

        log.debug("请求uri和对应参数日志===>>>{}", sb.toString());
        return params;
    }

    /**
     * 从body中获取参数，因为回调参数是在body中的
     * 注意：因为从流里读取参数只能执行一次，在拦截器方法内执行后，spring将不能从流中注入参数，需要我们手动设置
     *
     * @param request
     * @return
     */
    public static Map<String, String> getParamsFromRequestBody(HttpServletRequest request) {
        log.debug("从请求体输入流中获取参数");
        Map<String, String> params = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (Exception ex) {
            log.error("从请求body中获取参数异常", ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    log.error("关闭流失败", ex);
                }
            }
        }
        if (StringUtils.isNotBlank(stringBuilder.toString())) {
            params = JSONObject.parseObject(stringBuilder.toString(), Map.class);
        }
        log.debug("从请求body中获取的参数：{}", params);

        return params;
    }


}
