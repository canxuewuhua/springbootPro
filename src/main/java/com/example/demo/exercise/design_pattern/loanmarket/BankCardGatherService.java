package com.example.demo.exercise.design_pattern.loanmarket;

import com.example.demo.common.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  此处是在贷超项目中用到的设计模式，供参考！
 *  需要一个ContextFactoryService,工厂service，通过getBean找到相应的service
 *
 *  此service是通过传入的standardType，进行判断调用哪个service
 *  易于扩展，设计模式待定
 */
@Service
@Slf4j
public class BankCardGatherService {

    @Autowired
    private ContextFactoryService contextFactoryService;

    /**
     * 预绑卡发送短信
     *
     * @return
     */
    public ResultDTO sendSms() {
        String standardType = "XXX";
        IBankCardService bankCardService = (IBankCardService) contextFactoryService.getBean(standardType, IBankCardService.SERVER_NAME);

        return bankCardService.sendSms();
    }
}
