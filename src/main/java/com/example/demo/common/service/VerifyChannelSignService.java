package com.example.demo.common.service;

import com.example.demo.util.RSASignature;
import com.example.demo.util.RequestParamUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 通道验签
 */
@Service
@Slf4j
public class VerifyChannelSignService {

    private static final String MARKET_PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUv5XfuxjUtRC9uRNIsJ8PkaaV+x4RBqckch3M9GrBb6frtN/+4cJ45KsyVxZpLnSv6QwIxWXvKek0XhERMgFT90NBl+9ZL9PT+bMAOLE8B/KZrHEtmsTUjqNmY3ejP1iycDuBpdMkW6dYg52XHYI7GCgbhJf+HhiiCrFl+YT7eQIDAQAB";

    /**
     * 小米验签(代发)
     * 从request键值对中获取参数
     *
     * @return
     */
    public boolean verifyMipayLoanSign(HttpServletRequest request) {

        Map<String, String> params = RequestParamUtils.getParamsFromRequestNameValues(request);
        if (params.isEmpty()) {
            log.error("该方法参数为空，无法验证签名");
            return false;
        }
        String signContent = RSASignature.getSignContent(params);
        String sign = params.get("sign");
        boolean verifyResult = RSASignature.verifySignByPublicKey(signContent, sign, MARKET_PUB_KEY);
        if (!verifyResult) {
            log.error("小米回调验签失败, params = {}", params);
            return false;
        }
        log.debug("小米回调验签成功");
        return true;
    }

    /**
     * 小米验签(代扣)
     * 从request键值对中获取参数
     *
     * @return
     */
    public boolean verifyMipayRepaymentSign(HttpServletRequest request) {

        Map<String, String> params = RequestParamUtils.getParamsFromRequestNameValues(request);
        if (params.isEmpty()) {
            log.error("该方法参数为空，无法验证签名");
            return false;
        }
        String signContent = RSASignature.getSignContent(params);
        String sign = params.get("sign");
        boolean verifyResult = RSASignature.verifySignByPublicKey(signContent, sign, MARKET_PUB_KEY);
        if (!verifyResult) {
            log.error("小米回调验签失败, params = {}", params);
            return false;
        }
        log.debug("小米回调验签成功");
        return true;
    }

}
