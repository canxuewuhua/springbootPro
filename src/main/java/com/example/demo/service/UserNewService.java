package com.example.demo.service;

import com.example.demo.domain.UserVO;
import com.example.demo.mapper.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yongqiang.zhu
 * @date 2019/4/3 0:37
 */
@Service
@Slf4j
public class UserNewService {

	@Autowired
	private UserDAO userDao;

	public UserVO getUserInfo(int id){
		UserVO userVO = userDao.selectByPrimaryKey(id);
		return  userVO;
	}
}
