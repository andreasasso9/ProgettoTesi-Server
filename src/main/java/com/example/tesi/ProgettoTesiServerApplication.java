package com.example.tesi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan("com.example.tesi.entity")
public class ProgettoTesiServerApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoTesiServerApplication.class, args);
	}
}
