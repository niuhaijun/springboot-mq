package com.niu.rabbitmqconsumerconcurrence.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niuhaijun
 * @date 2019-01-17 17:44
 */
@Configuration
public class DirectQueueConfig {

	public static final String QUEUE_A = "direct.queueA";
	private static final boolean durable = false;
	private static final boolean autoDelete = true;
	private static final boolean exclusive = false;

	/**
	 * 队列A
	 */
	@Bean(name = QUEUE_A)
	public Queue queueA() {

		return new Queue(QUEUE_A, durable, exclusive, autoDelete, null);
	}

}
