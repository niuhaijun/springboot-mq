package com.niu.rabbitmqconsumerconcurrence.task;

import com.niu.rabbitmqconsumerconcurrence.config.DirectBindingConfig;
import com.niu.rabbitmqconsumerconcurrence.producer.Producer;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTask {

	private final AtomicInteger count = new AtomicInteger(0);

	@Autowired
	private Producer producer;

	@Scheduled(cron = "0/1 * * * * ?")
	public void sentMessages() {

		String routingKey = DirectBindingConfig.ROUTING_KEY_A;
		String message = String.valueOf(count.addAndGet(1));
		producer.sendMsg(message, routingKey);

		log.info("====> 已发送了{}个消息", count.get());
	}

}

