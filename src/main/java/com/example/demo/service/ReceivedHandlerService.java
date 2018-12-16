package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author yongqiang.zhu
 * @date 2018/12/16 14:12
 */
@Service
@Slf4j
@Transactional(rollbackFor = Throwable.class)
public class ReceivedHandlerService {
	public void executePrint(String userId){
		System.out.println("接收消息后处理"+userId);
		Date date =new Date();
		log.info("打印最后一行日志，结果userId是：{}，当前时间是：{}", userId, date);
	}
}
