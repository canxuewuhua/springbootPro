package com.example.demo.test.transaction;

import com.example.demo.service.EmployeeService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
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

    @Autowired
    private EmployeeService employeeService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean parent(){
        try {
            log.info("开始执行parent方法");
            employeeService.updateEmployeeAgeById();
            log.info("更新完");
            child();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean child(){
        int i =5;
        if (i/5 == 1){
            employeeService.updateEmployeeSalaryById();
        }
        return true;
    }

    public static void main(String[] args) {
        TransactionTest transactionTest = new TransactionTest();
        transactionTest.parent();
    }

}
