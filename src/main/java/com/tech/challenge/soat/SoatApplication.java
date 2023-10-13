package com.tech.challenge.soat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tech.challenge.soat.core.applications.feign.client")
public class SoatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoatApplication.class, args);
	}

}
