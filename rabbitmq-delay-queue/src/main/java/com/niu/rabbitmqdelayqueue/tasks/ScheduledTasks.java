package com.niu.rabbitmqdelayqueue.tasks;

import com.google.gson.Gson;
import com.niu.rabbitmqdelayqueue.config.DirectBindingConfig;
import com.niu.rabbitmqdelayqueue.producer.Producer;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author niuhaijun
 * @date 2018/11/15 19:17
 */
@Component
@Slf4j
public class ScheduledTasks {

	private final AtomicInteger counter = new AtomicInteger(0);
	private final Gson gson = new Gson();

	@Resource
	private Producer producer;

	@Scheduled(cron = "0/30 * * * * ?")
	public void sentMessages() {

		directSentMessage();
	}

	private void directSentMessage() {

		Message message = new Message();
		message.routingKey = DirectBindingConfig.ROUTING_KEY_A;
		message.content = "666";
		message.seq = counter.incrementAndGet();
		message.sendTime = new Date();
		String routingKey = DirectBindingConfig.ROUTING_KEY_A;
		producer.sendMsg(gson.toJson(message), routingKey);
	}


	static class Message implements Serializable {

		String routingKey;
		String content;
		int seq;
		Date sendTime;
	}
}

