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
	private ReceivedHandlerService receivedHandlerService;

	/**
	 * 监听器监听指定的Queue获取消息
	 * 注：没有测试MQ，所以在此注释了注解
	 */
	//@RabbitListener(queues = "${rabbitmq.queue.name.repayment}")
	//@RabbitHandler
	public void receiveAccrualRevenueReportMessage1(String userId, Channel channel, Message message) throws IOException, InterruptedException {
		log.info("第一个消费者执行权责发生制报表添加处理，对应的参数信息：{}", userId);
		consumerMessage(userId);
		Thread.sleep(1000);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		//*queue的持久化*是通过durable=true来实现的
		//Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,Map<String, Object> arguments) throws IOException
		//channel.queueDeclare("queue.persistent.name", true, false, false, null);

		// exclusive：排他队列，如果一个队列被声明为排他队列，该队列仅对首次申明它的连接可见，并在连接断开时自动删除。这里需要注意三点：
		// 1. 排他队列是基于连接可见的，同一连接的不同信道是可以同时访问同一连接创建的排他队列；
		// 2.“首次”，如果一个连接已经声明了一个排他队列，其他连接是不允许建立同名的排他队列的，这个与普通队列不同；
		// 3.即使该队列是持久化的，一旦连接关闭或者客户端退出，该排他队列都会被自动删除的，这种队列适用于一个客户端发送读取消息的应用场景。

		// autoDelete：自动删除，如果该队列没有任何订阅的消费者的话，该队列会被自动删除。这种队列适用于临时队列。

		//*消息的持久化*
		//如过将queue的持久化标识durable设置为true,则代表是一个持久的队列，那么在服务重启之后，也会存在，因为服务会把持久化的queue存放在硬盘上，当服务重启的时候，
		// 会重新什么之前被持久化的queue。队列是可以被持久化，但是里面的消息是否为持久化那还要看消息的持久化设置。也就是说，
		// 重启之前那个queue里面还没有发出去的消息的话，重启之后那队列里面是不是还存在原来的消息，这个就要取决于发生着在发送消息时对消息的设置了。
		//如果要在重启后保持消息的持久化必须设置消息是持久化的标识。

		//channel.basicPublish("exchange.persistent", "persistent", MessageProperties.PERSISTENT_TEXT_PLAIN, "persistent_test_message".getBytes());
		//这里的关键是：MessageProperties.PERSISTENT_TEXT_PLAIN
		//BasicProperties props这个参数了，这里看下BasicProperties的定义：
		//String contentType,//消息类型如：text/plain
		//String contentEncoding,//编码
		//Map<String,Object> headers,
		//Integer deliveryMode,//1:nonpersistent 2:persistent
		//Integer priority,//优先级
		//String correlationId,
		//String replyTo,//反馈队列
		//String expiration,//expiration到期时间

		//这里的deliveryMode=1代表不持久化，deliveryMode=2代表持久化。

		//exchange的持久化
		//上面阐述了队列的持久化和消息的持久化，如果不设置exchange的持久化对消息的可靠性来说没有什么影响，但是同样如果exchange
		//不设置持久化，那么当broker服务重启之后，exchange将不复存在，那么既而发送方rabbitmq producer就无法正常发送消息。
		//这里建议，同样设置exchange的持久化。exchange的持久化设置也特别简单，方法如下：
		//一般只需要：channel.exchangeDeclare(exchangeName, “direct/topic/header/fanout”, true);即在声明的时候讲durable字段设置为true即可。

		//进一步讨论
		//1.将queue，exchange, message等都设置了持久化之后就能保证100%保证数据不丢失了?
		//答案是否定的。
		//首先，从consumer端来说，如果这时autoAck=true，那么当consumer接收到相关消息之后，还没来得及处理就crash掉了，那么这样
		//也算数据丢失，这种情况也好处理，只需将autoAck设置为false(方法定义如下)，然后在正确处理完消息之后进行手动 ack（channel.basicAck）.

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
