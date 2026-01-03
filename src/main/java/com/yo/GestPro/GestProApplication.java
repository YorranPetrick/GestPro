package com.yo.GestPro;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class GestProApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestProApplication.class, args);
	}

}
