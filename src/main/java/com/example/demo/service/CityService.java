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
 * @date 2019/5/3 9:18
 */
@Service
//@Transactional
@Slf4j
public class CityService {

	@Autowired
	private CityDAO cityDAO;

	@Autowired
	private CityOtherService cityOtherService;

	/**
	 *  更新城市名称
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateCityName() {

        // 更新10001
		CityVO cityVO = cityDAO.selectByPrimaryKey("10001");
		cityVO.setCityName("北京海淀");
		cityDAO.updateByPrimaryKeySelective(cityVO);

		//updateOtherCityName();
		// 更新10002
		try{
			//updateOtherCityName();
			cityOtherService.updateOtherCityName();
		}catch (Exception e){
			log.error("在主方法上进行捕获异常");
		}


	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateOtherCityName() {
		CityVO cityVO = cityDAO.selectByPrimaryKey("10002");
		cityVO.setCityName("河南开封");
		cityDAO.updateByPrimaryKeySelective(cityVO);
		if (1 == 1){
			throw new RuntimeException("1111");
//			try{
//				throw new RuntimeException("1111");
//			}catch (Exception e){
//				log.error("此处抛出了异常，但是捕获了，吞了异常，不会回滚");
//			}
		}
	}

}
