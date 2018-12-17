package com.example.demo.test.transaction;

import com.example.demo.service.UserService;
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
    private UserService userService;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public boolean parent(){
        try {
            child();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public boolean child(){
        return true;
    }

}
