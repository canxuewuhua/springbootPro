package com.example.demo.exercise.design_pattern.loanmarket.msxf;

import com.example.demo.common.ResultDTO;
import com.example.demo.exercise.design_pattern.loanmarket.IBankCardService;
import com.example.demo.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service("MSXF" + "bankcardServie")
public class BankCardMsxfService implements IBankCardService {



    @Override
    public ResultDTO sendSms() {

        /**
         * 马上消费商户业务处理逻辑
         */

        return ResultUtils.success();
    }
}
