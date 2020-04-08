package com.niu.rabbitmqconsumerretry.tasks;

import com.niu.rabbitmqconsumerretry.config.MqConfig;
import com.niu.rabbitmqconsumerretry.config.Producer;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author niuhaijun
 * @date 2018/11/15 19:17
 */
@Component
@Slf4j
public class ScheduledTasks {

	private final AtomicInteger count = new AtomicInteger(0);

	@Autowired
	private Producer producer;

	@Scheduled(cron = "0/30 * * * * ?")
	public void sentMessages() {

		producer.sendMsg(count.incrementAndGet(), MqConfig.ROUTING_KEY);
	}

}

