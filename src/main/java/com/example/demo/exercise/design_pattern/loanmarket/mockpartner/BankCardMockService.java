package com.example.demo.exercise.design_pattern.loanmarket.mockpartner;


import com.example.demo.common.ResultDTO;
import com.example.demo.exercise.design_pattern.loanmarket.IBankCardService;
import com.example.demo.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 银行卡相关
 */
@Slf4j
@Service("MOCK_PARTNER" + "bankcardServie")
public class BankCardMockService implements IBankCardService {


    /**
     * 预绑卡发送短信
     *
     * @return
     */
    @Override
    public ResultDTO sendSms() {

        /**
         *  mock商户业务处理逻辑
         */
        return ResultUtils.success();
    }
}
