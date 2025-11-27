package com.microservice.carrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceCarritoApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCarritoApplication.class, args);
	}
}

