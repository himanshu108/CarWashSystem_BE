package com.example.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.example.demo.config.MessagingConfig;

@Component
public class User {
	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(User user) {
		System.out.println("Message received from Queue: " +user);
	}

}
