package com.example.demo.test.transaction;

import com.example.demo.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 嵌套事务
 */
@Slf4j
public class TransactionTest {

    @Autowired
    private CityService cityService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean parent(){
        try {
            log.info("开始执行parent方法");
            //cityService.updateCityName();
            log.info("更新完");
            //child();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean child(){
        int i =5;
        if (i/5 == 1){
            //cityService.updateCityName();
        }
        return true;
    }

    public static void main(String[] args) {
        TransactionTest transactionTest = new TransactionTest();
        transactionTest.parent();
    }

}
