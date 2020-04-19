package com.niu.rabbitmqconsumerconcurrence.producer;


import com.niu.rabbitmqconsumerconcurrence.config.DirectExchangeConfig;
import java.util.UUID;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author niuhaijun
 * @date 2019-01-15 16:46
 */
@Component
public class Producer {

	private RabbitTemplate rabbitTemplate;

	@Autowired
	public Producer(RabbitTemplate rabbitTemplate) {

		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMsg(String content, String routingKey) {

		CorrelationData correlationData = new CorrelationData(
			UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());

		rabbitTemplate.convertAndSend(DirectExchangeConfig.DIRECT_EXCHANGE, routingKey, content,
			correlationData);
	}
}
