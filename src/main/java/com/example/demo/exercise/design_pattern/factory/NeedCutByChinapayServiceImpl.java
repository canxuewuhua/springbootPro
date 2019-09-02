package com.example.demo.exercise.design_pattern.factory;

import org.springframework.stereotype.Service;

/**
 * @author yongqiang.zhu
 * @date 2019/9/2 23:14
 */
@Service("needCutByChinapayService")
public class NeedCutByChinapayServiceImpl implements NeedCutService{
	@Override
	public int createAllNeedCut() {
		return 8;
	}
}
