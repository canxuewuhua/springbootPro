package com.example.demo.dao;

import com.example.demo.domain.Customer;
import org.springframework.stereotype.Repository;

/**
 * Created by ZHUYONGQIANG on 2018/5/26.
 */
@Repository
public interface UserMapper {


    //根据客户id获取客户信息
    public Customer getCustomerMessageById(int id);
}
