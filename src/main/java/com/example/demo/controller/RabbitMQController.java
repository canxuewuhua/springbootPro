package com.example.demo.controller;

import com.example.demo.service.SendRabbitMQService;
import com.example.demo.util.SerialNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yongqiang.zhu
 * @date 2018/12/16 15:01
 */
@Slf4j
@RestController
@RequestMapping("/rabbitMQ")
public class RabbitMQController {

	@Autowired
	private SendRabbitMQService sendRabbitMQService;

	@RequestMapping("/sendMess")
	public void sayMess() throws InterruptedException {
		//String userId = "chuangzao101";
		//log.info("MQController的sayMessage：{}", userId);
		for (int i=0;i<500;i++){
			String userId = SerialNumberUtil.getPrimaryId();
			log.info("MQController的sayMessage生成的第：{}个userId{}", i, userId);
			Thread.sleep(1000);
			sendRabbitMQService.sendMQInfo(userId);
		}
	}
}
