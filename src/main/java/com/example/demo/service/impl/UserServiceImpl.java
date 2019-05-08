package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.Customer;
import com.example.demo.domain.UserVO;
import com.example.demo.domain.UserVOExample;
import com.example.demo.mapper.UserDAO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by ZHUYONGQIANG on 2018/5/26.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDAO userDAO;
    @Override
    public Customer getCustomerMessageById(int id) {
        return userMapper.getCustomerMessageById(id);
    }

    @Override
    public void updateUser() {
        userMapper.updateUser();
    }

    @Override
    public List<UserVO> getUserList() {
        UserVOExample userVOExample = new UserVOExample();
        List<UserVO> userList = userDAO.selectByExample(userVOExample);
        return userList;
    }

    @Override
    public List<Map<String, Object>> getListMapUser() {
        UserVOExample userVOExample = new UserVOExample();
        UserVOExample.Criteria criteria = userVOExample.createCriteria();
        criteria.andStNameEqualTo("lisi");
        List<Map<String,Object>> userList = userDAO.selectMapByExample(userVOExample);
        return userList;
    }
}
