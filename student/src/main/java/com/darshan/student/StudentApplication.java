package com.darshan.student;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan({"com.darshan.controller","com.darshan.service","com.darshan.circuitBreaker","com.darshan.feignMicrometerConfig"})
@EntityScan("com.darshan.entity")
@EnableJpaRepositories("com.darshan.repository")
@EnableFeignClients("com.darshan.feignClient")
public class StudentApplication {

	@Value("${address.service.url}")
	private String addressServiceUrl;

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@Bean
	public WebClient webClient(){
		WebClient webClient = WebClient.builder().baseUrl(addressServiceUrl).build();
		return  webClient;
	}

}
