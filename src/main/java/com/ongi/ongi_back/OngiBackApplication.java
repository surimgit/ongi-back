package com.ongi.ongi_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class OngiBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(OngiBackApplication.class, args);
	}

}