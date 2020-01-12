package com.example.demo.exercise.design_pattern.loanmarket.kingdom;

import com.example.demo.common.ResultDTO;
import com.example.demo.exercise.design_pattern.loanmarket.IBankCardService;
import com.example.demo.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 银行卡相关
 */
@Slf4j
@Service("KINGDOM" + "bankcardServie")
public class BankCardKingDomService implements IBankCardService {

    /**
     * 预绑卡发送短信
     *
     * @return
     */
    @Override
    public ResultDTO sendSms() {

        log.info("预绑卡发送短信,dto={}", "bankCardSendSmsRequestDTO");
        return ResultUtils.success();

        /*String partnerCode = bankCardSendSmsRequestDTO.getPartnerCode();
        String customerCode = bankCardSendSmsRequestDTO.getCustomerCode();

        String openId = customerVO.getOpenId();
        // 请求商户预绑卡发送短信
        String requestId = SerialNumberUtil.getRequestId();
        BankCardSendSmsHttpRequestDTO httpRequestDTO = new BankCardSendSmsHttpRequestDTO();
        httpRequestDTO.setOpenId(openId);
        httpRequestDTO.setRealName(bankCardSendSmsRequestDTO.getRealName());
        httpRequestDTO.setBankCardNo(bankCardSendSmsRequestDTO.getBankCardNo());
        httpRequestDTO.setCertificateNo(bankCardSendSmsRequestDTO.getCertificateNo());
        httpRequestDTO.setPhoneNo(bankCardSendSmsRequestDTO.getPhoneNo());

        // 组装请求信息
        Map<String, String> map;
        try {
            map = APIRequestUtils.getRequestMap(httpRequestDTO, requestId, partnerVO, privateKey);
        } catch (Exception e) {
            log.error("{}，预绑卡发送短信签名失败，partnerCode：{}，requestId：{}，openId：{}，异常信息：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, partnerCode, requestId, openId, e.getMessage(), e);
            return ResultUtils.fail(CodeMsg.SYSTEM_SIGN_ERROR);
        }

        // 获取请求的url
        String requestUrl = partnerVO.getPartnerDomain() + EnumAPIRequestUrl.BANK_CARD_SMS;
        log.info("预绑卡发送短信发起http请求，partnerCode：{}，requestId：{}，url：{}", partnerCode, requestId, requestUrl);
        String response;
        try {
            response = HttpClientUtil.sendPostJson(map, requestUrl);
            ResultDTO resultDTO = APIRequestUtils.checkPartnerResult(response, partnerVO, privateKey);
            return resultDTO;
        } catch (Exception e) {
            log.error("{}，预绑卡发送短信接口出现异常，requestId：{}，异常信息：{}", WarnConstant.LOAN_MARKET_CRITICAL_ERROR, requestId, e.getMessage(), e);
            return ResultUtils.fail(CodeMsg.SYSTEM_HTTP_ERROR);
        }*/
    }

}
