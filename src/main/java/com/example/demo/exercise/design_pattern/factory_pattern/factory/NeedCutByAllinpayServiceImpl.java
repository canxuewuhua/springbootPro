package com.example.demo.exercise.design_pattern.factory_pattern.factory;

import org.springframework.stereotype.Service;

/**
 * @author yongqiang.zhu
 * @date 2019/9/2 23:12
 */
@Service("needCutByAllinpayService")
public class NeedCutByAllinpayServiceImpl implements NeedCutService{
	@Override
	public int createAllNeedCut() {
		return 12;
	}
}
