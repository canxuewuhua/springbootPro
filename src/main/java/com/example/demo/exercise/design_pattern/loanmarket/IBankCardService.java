package com.example.demo.exercise.design_pattern.loanmarket;


import com.example.demo.common.ResultDTO;

public interface IBankCardService {

    String SERVER_NAME = "bankcardServie";

    /**
     * 预绑卡发送短信
     *
     * @return
     */
    ResultDTO sendSms();

}
