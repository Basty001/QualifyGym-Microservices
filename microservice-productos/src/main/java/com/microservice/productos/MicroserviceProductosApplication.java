package com.microservice.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceProductosApplication.class, args);
	}

}

