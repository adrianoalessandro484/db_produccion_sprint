package com.produccion.db_produccion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class DbProduccionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbProduccionApplication.class, args);
	}

}