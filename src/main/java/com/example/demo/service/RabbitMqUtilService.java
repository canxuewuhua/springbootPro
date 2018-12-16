package com.example.demo.service;

import com.example.demo.dto.RepaymentMqDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * rabbitMQ工具类
 */
@Service
@Slf4j
public class RabbitMqUtilService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.name.repayment}")
    private String queueNameRepayment;


    /**
     * 将需要扣款的客户还款计划ID放入消息对列中
     */
    public void sendCustInoutPlanLedgerIdToMQ(String userId) {
        this.rabbitTemplate.convertAndSend(queueNameRepayment, userId);
        log.info("添加正常rabbitMq消息成功userId：{}", userId);
    }

}
