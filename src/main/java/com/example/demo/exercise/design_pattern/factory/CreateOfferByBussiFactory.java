package com.example.demo.exercise.design_pattern.factory;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yongqiang.zhu
 * @date 2019/9/2 23:16
 */
@Service
public class CreateOfferByBussiFactory {

	@Resource(name= "needCutByAllinpayService")
	private NeedCutService createNeedCutByAllinpayService;

	@Resource(name = "needCutByChinapayService")
	private NeedCutService createNeedCutByChinapayService;

	public NeedCutService getCreateService(String m){
		if (m.equals("1")){
			return createNeedCutByAllinpayService;
		}else if (m.equals("2")){
			return createNeedCutByChinapayService;
		}
		return null;
	}
}
