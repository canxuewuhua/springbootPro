package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.example.demo.constant.WarnConstant;
import com.example.demo.dto.ThirdCompanyHeadDTO;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    protected static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    private static final int MAX_TIMEOUT = 30 * 1000;
    private static final String CHARSET = "UTF-8";
    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;

    static {
        // 设置连接池
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        requestConfig = configBuilder.build();
    }

    public static String sendPostJson(Map<String, String> parmMap, String url) throws Exception {
        String result = null;
        CloseableHttpClient httpclient = null;
        HttpPost httppost;
        HttpResponse response;
        try {
            httpclient = HttpClients.createDefault();
            httppost = new HttpPost(url);
            httppost.setConfig(requestConfig);
            // 解决中文乱码问题
            String json = JSON.toJSONString(parmMap);
            StringEntity entitys = new StringEntity(json, ContentType.APPLICATION_JSON);
            if (json != null && json.length() > 10000) {
                log.info("请求地址：{}，请求报文长度：{}，请求报文(截取前10000个字符)：{}", url, json.length(), json.substring(0, 10000));
            } else {
                log.info("请求地址：{}，请求报文：{}", url, json);
            }

            httppost.setEntity(entitys);
            response = httpclient.execute(httppost);
            log.info("请求地址：{}，返回报文：{}", url, response);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                log.error("{}，http请求失败，请求地址：{}，状态码：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, statusCode);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            result = EntityUtils.toString(entity, CHARSET);
            log.info("http请求成功，result：{}", result);
            httppost.abort();
        } catch (ConnectException e) {
            // 连接异常的话当做请求支付网关失败，自然这次就是发起失败了
            log.error("{}，http连接超时ConnectException，请求地址：{}，异常信息：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, e.getMessage(), e);
        } catch (ConnectTimeoutException e) {
            // 连接异常的话当做请求支付网关失败，自然这次就是发起失败了
            log.error("{}，http连接超时ConnectTimeoutException，请求地址：{}，异常信息：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, e.getMessage(), e);
        } catch (SocketTimeoutException e) {
            // 响应异常的话就不知道支付网关正确处理了没有，保守起见只能当做支付网关收到成功
            log.error("{}，http响应超时，请求地址：{}，异常信息：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("{}，http请求异常，请求地址：{}，异常信息：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, e.getMessage(), e);
            throw e;
        } finally {
            if (null != httpclient) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    log.error("关闭httpclient异常", e);
                }
            }
        }
        return result;
    }

    public static String sendPostObjectMapJson(Map<String, Object> parmMap, String url, Map<String, String> headersMap) throws Exception {
        String result = null;
        CloseableHttpClient httpclient = null;
        HttpPost httppost;
        HttpResponse response;
        try {
            httpclient = HttpClients.createDefault();
            httppost = new HttpPost(url);
            httppost.setConfig(requestConfig);

            // 设置header信息
            if (headersMap != null) {
                for (String name : headersMap.keySet()) {
                    httppost.setHeader(name, headersMap.get(name));
                }
            }

            // 解决中文乱码问题
            String json = JSON.toJSONString(parmMap);
            StringEntity entitys = new StringEntity(json, CHARSET);
            if (json != null && json.length() > 10000) {
                log.info("请求地址：{}，请求报文长度：{}，请求报文(截取前10000个字符)：{}，", url, json.length(), json.substring(0, 10000));
            } else {
                log.info("请求地址：{}，请求报文：{}", url, json);
            }
            entitys.setContentEncoding("UTF-8");
            entitys.setContentType("application/json");
            httppost.setEntity(entitys);
            response = httpclient.execute(httppost);
            log.info("请求地址：{}，返回报文：{}", url, response);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                log.error("{}，http请求失败，请求地址：{}，状态码：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, statusCode);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            result = EntityUtils.toString(entity, CHARSET);
            log.info("http请求成功，result：{}", result);
            httppost.abort();
        } catch (ConnectException e) {
            // 连接异常的话当做请求支付网关失败，自然这次就是发起失败了
            log.error("{}，http连接超时ConnectException，请求地址：{}，异常信息：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, e.getMessage(), e);
        } catch (ConnectTimeoutException e) {
            // 连接异常的话当做请求支付网关失败，自然这次就是发起失败了
            log.error("{}，http连接超时ConnectTimeoutException，请求地址：{}，异常信息：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, e.getMessage(), e);
        } catch (SocketTimeoutException e) {
            // 响应异常的话就不知道支付网关正确处理了没有，保守起见只能当做支付网关收到成功
            log.error("{}，http响应超时，请求地址：{}，异常信息：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("{}，http请求异常，请求地址：{}，异常信息：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, e.getMessage(), e);
            throw e;
        } finally {
            if (null != httpclient) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    log.error("关闭httpclient异常", e);
                }
            }
        }
        return result;
    }

    /**
     * 通过request获取head中的信息
     *
     * @param request
     * @return
     */
    public static ThirdCompanyHeadDTO getLoanMarketHeadDTO(HttpServletRequest request) {
        ThirdCompanyHeadDTO thirdCompanyHeadDTO = new ThirdCompanyHeadDTO();
        Class headClass = thirdCompanyHeadDTO.getClass();
        Field[] fields = headClass.getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();
            String value = request.getHeader(key);
            try {
                field.setAccessible(true);
                field.set(thirdCompanyHeadDTO, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        log.info("获取获取head中的信息，thirdCompanyHeadDTO：{}", thirdCompanyHeadDTO);
        return thirdCompanyHeadDTO;
    }

    public static String sendPost(Map<String, String> parmMap, String url) throws Exception {
        String result = null;
        CloseableHttpClient httpclient = null;
        HttpPost httppost;
        HttpResponse response;
        try {
            httpclient = HttpClients.createDefault();
            httppost = new HttpPost(url);
            httppost.setConfig(requestConfig);
            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, String> entry : parmMap.entrySet()) {
                if (StringUtils.isEmpty(entry.getValue())) {
                    continue;
                }
                BasicNameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                params.add(pair);
            }
            log.info("请求地址：{}，请求参数对：{}", url, parmMap);
            // 解决中文乱码问题
            httppost.setEntity(new UrlEncodedFormEntity(params, CHARSET));
            response = httpclient.execute(httppost);
            log.info("请求地址：{}，返回报文：{}", url, response);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                log.error("{}，http请求失败，请求地址：{}，状态码：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, statusCode);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            result = EntityUtils.toString(entity, CHARSET);
            log.info("http请求成功，result：{}", result);
            httppost.abort();
        } catch (ConnectException e) {
            // 连接异常的话当做请求支付网关失败，自然这次就是发起失败了
            log.error("{}，http连接超时ConnectException，请求地址：{}，异常信息：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, e.getMessage(), e);
        } catch (ConnectTimeoutException e) {
            // 连接异常的话当做请求支付网关失败，自然这次就是发起失败了
            log.error("{}，http连接超时ConnectTimeoutException，请求地址：{}，异常信息：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, e.getMessage(), e);
        } catch (SocketTimeoutException e) {
            // 响应异常的话就不知道支付网关正确处理了没有，保守起见只能当做支付网关收到成功
            log.error("{}，http响应超时，请求地址：{}，异常信息：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("{}，http请求异常，请求地址：{}，异常信息：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, url, e.getMessage(), e);
            throw e;
        } finally {
            if (null != httpclient) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    log.error("关闭httpclient异常", e);
                }
            }
        }
        return result;
    }

    /**
     * 将对象转换为map
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }
}
