package com.natal.eligibilityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EligibilityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EligibilityServiceApplication.class, args);
	}

}
