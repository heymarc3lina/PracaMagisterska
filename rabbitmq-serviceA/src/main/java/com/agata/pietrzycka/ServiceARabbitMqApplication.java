package com.agata.pietrzycka;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class ServiceARabbitMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceARabbitMqApplication.class, args);
	}

}
