package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.Customer;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZHUYONGQIANG on 2018/5/26.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public Customer getCustomerMessageById(int id) {
        return userMapper.getCustomerMessageById(id);
    }
}
