package com.example.demo.exercise.design_pattern.loanmarket;

import com.example.demo.common.CodeMsg;
import com.example.demo.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContextFactoryService {

    @Autowired
    private ApplicationContext applicationContext;

    public Object getBean(String standardType, String serviceName) {

        if (StringUtils.isBlank(standardType)){
            log.error("{}，通过ApplicationContext获取Bean失败，standardType 为空, serviceName = {}", "WarnConstant.LOAN_MARKET_CRITICAL_ERROR", standardType, serviceName);
            throw new ServiceException(CodeMsg.QUERY_PARTNER_SERVICE_FAILURE);
        }
        try {
            return applicationContext.getBean(standardType + serviceName);
        } catch (Exception e) {
            log.error("{}，通过ApplicationContext获取Bean失败，standardType = {}, serviceName = {}", "LOAN_MARKET_CRITICAL_ERROR", standardType, serviceName);
            throw e;
        }

    }

}
