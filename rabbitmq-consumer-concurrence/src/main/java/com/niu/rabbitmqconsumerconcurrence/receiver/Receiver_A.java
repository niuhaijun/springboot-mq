package com.niu.rabbitmqconsumerconcurrence.receiver;


import com.niu.rabbitmqconsumerconcurrence.config.DirectQueueConfig;
import com.rabbitmq.client.Channel;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = DirectQueueConfig.QUEUE_A, containerFactory = "containerFactory")
@Slf4j
public class Receiver_A {

	private static final Random random = new Random();

	@RabbitHandler
	public void process(String content, Channel channel,
		@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag)
		throws Exception {

		Thread.sleep(3000);

		int val = random.nextInt(2);
		if (val % 2 == 0) {
			channel.basicAck(deliveryTag, false);
			log.info("Receiver_A 从 QUEUE_A 中接收到的信息是--> {}", content);
		}
		else {
			channel.basicReject(deliveryTag, true);
		}
	}

}
