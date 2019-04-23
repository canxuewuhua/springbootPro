package com.example.demo.service;

import com.example.demo.domain.Customer;
import com.example.demo.domain.UserVO;

import java.util.List;
import java.util.Map;

/**
 * Created by ZHUYONGQIANG on 2018/5/26.
 */
public interface UserService {
    public Customer getCustomerMessageById(int id);
    public void updateUser();

    public List<UserVO> getUserList();

    public List<Map<String, Object>> getListMapUser();
}
