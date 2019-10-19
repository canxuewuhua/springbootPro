package com.example.demo.exercise.design_pattern.loanmarket.mswj;

import com.example.demo.common.ResultDTO;
import com.example.demo.exercise.design_pattern.loanmarket.IBankCardService;
import com.example.demo.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service("MSWJ" + "bankcardServie")
public class BankCardMswjService implements IBankCardService {



    @Override
    public ResultDTO sendSms() {

        /**
         * 马上我家商户业务处理逻辑
         */

        return ResultUtils.success();
    }
}
