package com.rfid.consumer.rfidconsumer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class RfidConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RfidConsumerApplication.class, args);
	}

}