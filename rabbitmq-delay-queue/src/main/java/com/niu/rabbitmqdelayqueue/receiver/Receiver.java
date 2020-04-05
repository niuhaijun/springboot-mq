package com.niu.rabbitmqdelayqueue.receiver;

import com.niu.rabbitmqdelayqueue.config.DirectQueueConfig;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author niuhaijun
 * @date 2019-01-22 23:49
 */
@Component
@RabbitListener(queues = {DirectQueueConfig.QUEUE_B})
@Slf4j
public class Receiver {

	@RabbitHandler
	public void process(String content) {

		log.info("====> now = {}, content = {}", new Date(), content);
	}
}
