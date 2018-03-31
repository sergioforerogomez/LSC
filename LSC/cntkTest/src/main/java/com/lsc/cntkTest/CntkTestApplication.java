package com.lsc.cntkTest;

import com.lsc.cntkTest.services.CNTKService;
import com.lsc.cntkTest.services.CNTKServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CntkTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(CntkTestApplication.class, args);
	}

	@Bean("cntk")
	public CNTKService cntkService() { return new CNTKServiceImpl(); }

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}
}
