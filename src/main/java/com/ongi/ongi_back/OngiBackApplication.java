package com.ongi.ongi_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OngiBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(OngiBackApplication.class, args);
	}

}