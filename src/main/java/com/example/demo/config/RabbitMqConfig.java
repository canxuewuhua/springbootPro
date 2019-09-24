package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 如果没有测试MQ，将该处的注解注掉，防止程序去连接rabbitMQ
 */
@Configuration
@EnableCaching
@Slf4j
public class RabbitMqConfig implements RabbitTemplate.ReturnCallback {


    @Value("${rabbitmq.queue.name.repayment}")
    private String queueName;


    @Bean
    public ConnectionFactory invokeContainerFactory() throws Exception {

//        KeyCenterConstants.getKeyCenterProperties(KEY_CENTER_PATH);
//        String response = KeyCenterUtil.getKeyCenterValue(rabbitmqConfigKey);
//        if (StringUtils.isBlank(response)) {
//            log.error("rabbitmq初始化失败，从keycenter获取数据库配置失败,配置的key为：{}", rabbitmqConfigKey);
//            throw new Exception("rabbitmq初始化失败，从keycenter获取数据库配置失败");
//        }

        //RabbitMQConfigDTO rabbitMQConfigDTO = JSONObject.parseObject(response, RabbitMQConfigDTO.class);

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("192.168.199.239");
        //connectionFactory.setHost("120.131.15.17");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        //connectionFactory.setUsername("ksrong");
        connectionFactory.setPassword("123456");
        //connectionFactory.setPassword("pvf6ujK7AJkmeVIN");
        connectionFactory.setPublisherConfirms(true);

        log.info("rabbitMQ初始化成功");
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() throws Exception {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(invokeContainerFactory());
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                log.error("{}将需要扣款的客户还款计划ID放入消息对列中失败，correlationData:{}  ack：{}，cause：{}", "严重警告", correlationData, ack, cause);
            } else {
                log.info("将需要扣款的客户还款计划ID放入消息对列中成功，correlationData:{}  ack：{}，cause：{}", correlationData, ack, cause);
            }
        });
        return rabbitTemplate;
    }


    /**
     * 初始化正常队列
     */
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }


    /**
     * 当路由不到队列时返回给消息发送者
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("当路由不到队列时返回给消息发送者:replyCode：{}，replyText：{}，exchange：{}，routingKey：{}", replyCode, replyText, exchange, routingKey);
    }

}
