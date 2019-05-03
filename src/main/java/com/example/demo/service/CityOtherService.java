package com.example.demo.service;

import com.example.demo.domain.CityVO;
import com.example.demo.mapper.CityDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yongqiang.zhu
 * @date 2019/5/3 14:18
 */
@Service
//@Transactional
@Slf4j
public class CityOtherService {

	@Autowired
	private CityDAO cityDAO;
	/**
	 *  更新城市名称
	 */
	@Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
	public void updateOtherCityName() {
		CityVO cityVO = cityDAO.selectByPrimaryKey("10002");
		cityVO.setCityName("河南开封");
		cityDAO.updateByPrimaryKeySelective(cityVO);
		int i = 5;
		if (i/0 == 1){
			System.out.println("方法不会执行的");
		}

	}
}
