package com.ongi.ongi_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class OngiBackApplication {

	// branch 생
	public static void main(String[] args) {
		SpringApplication.run(OngiBackApplication.class, args);
	}

}
