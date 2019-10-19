package com.example.demo.test.sign;

import com.example.demo.domain.CoreProxyAbutmentMerchantVO;
import com.example.demo.service.AbutmentMerchantService;
import com.example.demo.util.Md5Utils;
import com.example.demo.util.RequestParamUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class InternalConnectionService {

    @Autowired
    private AbutmentMerchantService abutmentMerchantService;

    /**
     * md5验签
     */
    public boolean verifyDefaultSign(HttpServletRequest request) {

        Map<String, String> params = RequestParamUtils.getParamsFromRequestNameValues(request);

        if (params.isEmpty()) {
            log.error("该方法参数为空，无法验证签名");
            return false;
        }

        String accessAppId = params.get("accessAppId");
        String accessKey = params.get("accessKey");
        String timestamp = params.get("timestamp");
        String sign = params.get("sign");

        if (StringUtils.isEmpty(accessAppId) || StringUtils.isEmpty(accessKey) || StringUtils.isEmpty(timestamp)) {
            log.error(" ParameterFilter accessMerNo or accessKey is null accessAppId={} ,accessKey={},timestamp={}", accessAppId, accessKey, timestamp);
            return false;
        }

        String sk;
        try {
            // 获取AKSK
            CoreProxyAbutmentMerchantVO abutmentMerchant = abutmentMerchantService.getCoreProxyAbutmentMerchant(accessAppId, accessKey);
            sk = abutmentMerchant.getSecretKey();
        } catch (Exception e) {
            log.error("查询accessKey失败，accessKey={}，异常信息：{}", accessKey, e.getMessage(), e);
            return false;
        }

        String signContent = Md5Utils.getSignContent(params, sk);

        log.debug("signContent={}", signContent);
        String mySign = Md5Utils.getMD5Encrypt(signContent).toLowerCase();;
        log.debug("mysign={}", mySign);
        if (StringUtils.isBlank(sign) || !sign.equalsIgnoreCase(mySign)) {
            log.error("验签失败, params = {}", params);
            return false;
        }
        log.debug("验签成功");

        return true;
    }
}
