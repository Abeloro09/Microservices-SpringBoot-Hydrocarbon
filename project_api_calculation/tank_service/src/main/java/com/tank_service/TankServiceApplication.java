package com.tank_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class TankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TankServiceApplication.class, args);
	}

}
