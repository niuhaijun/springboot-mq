package com.niu.rabbitmqconsumerretry.config;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author niuhaijun
 * @date 2020-03-29 15:49
 * @version 1.0
 * @description: xxx
 */
@Component
@RabbitListener(queues = MqConfig.QUEUE_NAME)
@Slf4j
public class Consumer {

	@RabbitHandler
	public void process(Integer msgContent) {

		log.info("====> nowTime = {}, msgContent = {}", LocalDateTime.now(), msgContent);

		if (msgContent % 2 == 0) {
			int num = 1 / 0;
		}
	}

}