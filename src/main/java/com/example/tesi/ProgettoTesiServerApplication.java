package com.example.tesi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.tesi.entity")
public class ProgettoTesiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoTesiServerApplication.class, args);
	}

}
