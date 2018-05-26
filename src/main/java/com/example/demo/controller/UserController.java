package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.Customer;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZHUYONGQIANG on 2018/5/26.
 */
@RestController
@RequestMapping("/project")
public class UserController {
    @Value("${user.namevalue}}")
    String namevalue;
    @Value("${user.id}}")
    String id;

    @Autowired
    private UserService userService;

    @RequestMapping("/say")
    public String sayHello(){
        String str = "Hello World";
        return str;
    }

    @RequestMapping("/getUser")
    public String getUser(){
        String str = namevalue+id;
        return str;
    }

    @RequestMapping("/getUserMessageFromDB")
    public String getUserMessageFromDB(int id){
        Customer customer = userService.getCustomerMessageById(id);
        return JSON.toJSON(customer).toString();
    }
}
