package com.example.demo.exercise.design_pattern.factory_pattern.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yongqiang.zhu
 * @date 2019/9/2 23:41
 * 可以用在路由的类上
 */
@RestController
@RequestMapping("/factory")
public class FactoryController {
	@Autowired
	private CreateOfferByBussiFactory createOfferByBussiFactory;

	@RequestMapping(name = "POST请求", path = "/getCount", method = RequestMethod.POST)
	public int getValue(){
		NeedCutService createService = createOfferByBussiFactory.getCreateService("1");
		int count = createService.createAllNeedCut();
		return count;
	}
}
