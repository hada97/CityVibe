package com.start.CityVibe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class CityVibeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityVibeApplication.class, args);
		System.out.println("============================");
		System.out.println("========IN EXECUTION========");
		System.out.println("============================");
	}

}
