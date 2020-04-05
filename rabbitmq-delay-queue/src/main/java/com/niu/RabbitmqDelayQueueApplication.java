package com.niu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitmqDelayQueueApplication {

	public static void main(String[] args) {

		SpringApplication.run(RabbitmqDelayQueueApplication.class, args);
	}

}
