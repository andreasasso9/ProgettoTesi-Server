package com.example.tesi;

import com.example.tesi.interceptor.UserSaveInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan("com.example.tesi.entity")
@Configuration
public class ProgettoTesiServerApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoTesiServerApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserSaveInterceptor()).addPathPatterns("/users/save");
	}
}
