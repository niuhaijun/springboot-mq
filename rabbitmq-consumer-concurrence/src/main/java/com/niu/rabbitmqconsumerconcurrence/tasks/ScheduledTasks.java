package com.niu.rabbitmqconsumerconcurrence.tasks;

import static java.lang.String.format;

import com.niu.rabbitmqconsumerconcurrence.exchange.DirectBindingConfig;
import com.niu.rabbitmqconsumerconcurrence.exchange.DirectProducer;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.LongStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private final AtomicInteger count = new AtomicInteger(0);

	@Autowired
	private DirectProducer directProducer;

	@Scheduled(cron = "0/30 * * * * ?")
	public void sentMessages() {

		directSentMessage();
	}

	private void directSentMessage() {

		LongStream.range(0, 10)
			.forEach(t -> {
				String routingKey = DirectBindingConfig.ROUTING_KEY_A;
				String message = format("【这是DirectProducer发送的第%s个消息, 创建消息时间是%s, 路由键是%s】",
					count.addAndGet(1), LocalDateTime.now(), routingKey);
				directProducer.sendMsg(message, routingKey);
			});
	}
}

