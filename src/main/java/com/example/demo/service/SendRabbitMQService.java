package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author yongqiang.zhu
 * @date 2018/12/16 14:57
 */
@Service
@Slf4j
@Transactional(rollbackFor = Throwable.class)
public class SendRabbitMQService {

	@Resource
	private RabbitMqUtilService rabbitMqUtilService;

	/**
	 * 记账成功后，如果该还款计划已经结清，并且在宽限期内完结
	 * 使用队列
	 */
	public void sendMQInfo(String userId) {
			log.info("记账成功后，使用消息队列，调用报表添加接口，初始参数：{}", userId);
			rabbitMqUtilService.sendCustInoutPlanLedgerIdToMQ(userId);
		}
}
