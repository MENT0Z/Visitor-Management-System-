package com.example.pre_approve_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PreApproveServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreApproveServiceApplication.class, args);
	}

}
