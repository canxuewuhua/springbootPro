package com.example.demo.service;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
public class RabbitMqReceiveService {

	@Autowired
	private RabbitMqUtilService rabbitMqUtilService;
	@Autowired
	private ReceivedHandlerService receivedHandlerService;

	/**
	 * 监听器监听指定的Queue获取消息
	 */
	@RabbitListener(queues = "${rabbitmq.queue.name.repayment}")
	@RabbitHandler
	public void receiveAccrualRevenueReportMessage1(String userId, Channel channel, Message message) throws IOException {
		log.info("第一个消费者执行权责发生制报表添加处理，对应的参数信息：{}", userId);
		consumerMessage(userId);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

	private void consumerMessage(String userId){
		log.info("调用权责发生制报表添加接口，初始参数：{}", userId);
		try {
//			String contractLedgerId = accrualBasisReportMQDTO.getCotnractLedgerId();
//			BigDecimal formulaFine = accrualBasisReportMQDTO.getFormulaFine();
//			BigDecimal formulaInterestRate = accrualBasisReportMQDTO.getFormulaInterestRate();
			receivedHandlerService.executePrint(userId);
		} catch (Exception e) {
			log.error("{}，还款记账成功权责发生制报表队列添加发生异常，{}", "严重警告", e.getMessage(), e);
		}
	}
}
