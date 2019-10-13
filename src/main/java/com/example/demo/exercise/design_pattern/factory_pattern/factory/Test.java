package com.example.demo.exercise.design_pattern.factory_pattern.factory;

import javax.annotation.Resource;

/**
 * @author yongqiang.zhu
 * @date 2019/9/2 23:26
 */
public class Test {
	@Resource(name = "aaa")
	private CreateOfferByBussiFactory createOfferByBussiFactory;
	public int getValue(){
		NeedCutService createService = createOfferByBussiFactory.getCreateService("1");
		int count = createService.createAllNeedCut();
		return count;
	}

	public static void main(String[] args) {
		Test test = new Test();
		System.out.println(test.getValue());
	}
}
