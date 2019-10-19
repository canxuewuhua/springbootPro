package com.example.demo.filter;

import com.example.demo.common.CodeMsg;
import com.example.demo.domain.CoreProxyAbutmentMerchantVO;
import com.example.demo.service.AbutmentMerchantService;
import com.example.demo.util.JsonUtil;
import com.example.demo.util.Md5Utils;
import com.example.demo.vo.BaseReturn;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * RSA解密和验证签名
 *
 * 该类是核心代理使用的访问接口的签名认证
 * 这个签名的过程是：在验签的时候将商户的私钥在数据库表中进行获取，拿这个私钥对参数内容再来一次加签mysign
 * 拿刚生成的这个mysign和接口传过来的sign进行比较，如果相等，则签名通过。
 *
 *  如：内部签名验证（可以使用下方的签名验证方法） 小米支付代发签名验证 小米支付代扣签名验证（需要拿对方的公钥对sign进行解密和content进行校验（验签））
 * 总结：这个适用于在本部门内的系统访问使用，因为你可以存储另外一个系统的私钥，但是在访问或者被访问第三方公司的接口时，是无法获取到对方的私钥的
 * 所以会使用另外一个和支付gateway和dai超一样的一个签名方法
 *
 * 这个类不用了，有一个更好的方法可以使用 用aop扫描controller的方法 [ApiAuthAop]
 */
//@WebFilter(filterName = "parameterFilter", urlPatterns = {"/v1/*", "/v2/*"})
public class ParameterFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(ParameterFilter.class);

    @Autowired
    private AbutmentMerchantService abutmentMerchantService;

    //对接商户使用标识 0未使用  1使用中
    public final static String ABUTMENT_MERCHANT_USE_FLAG = "1";

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Filter initialized");
    }

    public void destroy() {
        System.out.println("Filter destroyed");
    }


    @SuppressWarnings("rawtypes")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            String accessAppId = request.getParameter("accessAppId");
            String accessKey = request.getParameter("accessKey");
            String timestamp = request.getParameter("timestamp");
            if(StringUtils.isEmpty(accessAppId) || StringUtils.isEmpty(accessKey) || StringUtils.isEmpty(timestamp)){
                logger.error(" ParameterFilter accessMerNo or accessKey is null accessAppId={} ,accessKey={},timestamp={}",accessAppId,accessKey,timestamp);
                returnErrorMsg(httpServletResponse, CodeMsg.PARAMETER_ABSENT);
                return;
            }
            Enumeration params = request.getParameterNames();
            List<NameValuePair> nameValuePairsParams = new ArrayList<>();
            ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper((HttpServletRequest) request);
            //
            CoreProxyAbutmentMerchantVO abutmentMerchant = abutmentMerchantService.getCoreProxyAbutmentMerchant(accessAppId,accessKey);
            if(null == abutmentMerchant){
            	logger.error(" ParameterFilter abutmentMerchant is null, accessAppId={} ,accessKey={}",accessAppId,accessKey);
                returnErrorMsg(httpServletResponse,CodeMsg.NOT_FIND_APP_ID);
            	return;
            }else if(!ABUTMENT_MERCHANT_USE_FLAG.equals(abutmentMerchant.getUseFlag())){
                //对接商户使用标识 0未使用  1使用中
                logger.error(" ParameterFilter 对接商户未使用, accessAppId={} ,accessKey={}",accessAppId,accessKey);
                returnErrorMsg(httpServletResponse,CodeMsg.ABUTMENT_MERCHANT_NOT_USE);
                return;
            }else {
                if(abutmentMerchant.getUseBeginDate() == null || abutmentMerchant.getUseEndTime() == null){
                    //对接商户使用标识 0未使用  1使用中
                    logger.error(" ParameterFilter 对接商户使用时效错误, accessAppId={} ,accessKey={}",accessAppId,accessKey);
                    returnErrorMsg(httpServletResponse,CodeMsg.ABUTMENT_MERCHANT_USE_TIME_ERROR);
                    return;
                }
                Calendar calendar=Calendar.getInstance();
                if(!(calendar.getTime().after(abutmentMerchant.getUseBeginDate()) && calendar.getTime().before(abutmentMerchant.getUseEndTime()))){
                    //对接商户使用标识 0未使用  1使用中
                    logger.error(" ParameterFilter 对接商户使用时效错误, accessAppId={} ,accessKey={}",accessAppId,accessKey);
                    returnErrorMsg(httpServletResponse,CodeMsg.ABUTMENT_MERCHANT_USE_TIME_ERROR);
                    return;
                }
            }
            while (params.hasMoreElements()) {
                String name = params.nextElement().toString();
                String[] values = request.getParameterValues(name);
                if (null != values && values.length > 0) {
                    for (String value : values) {
                        nameValuePairsParams.add(new BasicNameValuePair(name, value));
                    }
                }
            }
            String uri = httpServletRequest.getRequestURI();
            logger.debug("请求参数日志===>>> uri={} ,data={}" ,uri,JsonUtil.toJson(nameValuePairsParams));
            //验证签名是否正确
            boolean verify_flag = false;
            String mySignContent = Md5Utils.getSignContent(nameValuePairsParams,abutmentMerchant.getSecretKey());
            String mySign = Md5Utils.getMD5Encrypt(mySignContent).toString().toLowerCase();
            String otherSign = request.getParameter("sign");

            if (!StringUtils.isEmpty(mySignContent) && !StringUtils.isEmpty(otherSign)) {
            	  if(otherSign.toLowerCase().equals(mySign)){
            		  verify_flag = true;
            	  }
            }
            logger.debug("请求 sign={} ,mySign={}",otherSign,mySign);
            if (!verify_flag) {
                returnErrorMsg(httpServletResponse,CodeMsg.VERIFY_SIGN_FAILED);
            } else {
                filterChain.doFilter(requestWrapper, response);
            }
        } catch (Exception e) {
            logger.error("签名验证出错", e);
            returnErrorMsg(httpServletResponse,CodeMsg.VERIFY_SIGN_FAILED);
        }

    }

    private void returnErrorMsg(HttpServletResponse response,CodeMsg codeMsg) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = null;
        response.setStatus(500);
        try {
            printWriter = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BaseReturn baseReturn = new BaseReturn();
        baseReturn.setCode(codeMsg.getErrorCode());
        baseReturn.setMsg(codeMsg.getErrorMsg());
        printWriter.write(JsonUtil.toJson(baseReturn));
    }

}
