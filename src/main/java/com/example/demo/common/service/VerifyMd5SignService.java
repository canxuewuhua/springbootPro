package com.example.demo.common.service;

import com.example.demo.service.AbutmentMerchantService;
import com.example.demo.util.Md5Utils;
import com.example.demo.util.RequestParamUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class VerifyMd5SignService {

    @Autowired
    private AbutmentMerchantService akService;

    /**
     * md5验签
     */
    public boolean verifyDefaultSign(HttpServletRequest request) {

        Map<String, String> params = RequestParamUtils.getParamsFromRequestNameValues(request);

        if (params.isEmpty()) {
            log.error("该方法参数为空，无法验证签名");
            return false;
        }

        String sign = params.get("sign");
        String ak = params.get("ak");
        String sk;
        try {
            sk = akService.getSkByAk(ak);
        } catch (Exception e) {
            log.error("查询ak失败，ak={}", ak, e);
            return false;
        }

        String signContent = Md5Utils.getSignContent(params, sk);

        log.debug("signContent={}", signContent);
        String mySign = Md5Utils.getMD5Encrypt(signContent);
        log.debug("mysign={}", mySign);
        if (StringUtils.isBlank(sign) || !sign.equalsIgnoreCase(mySign)) {
            log.error("验签失败, params = {}", params);
            return false;
        }
        log.debug("验签成功");
        return true;
    }

}
