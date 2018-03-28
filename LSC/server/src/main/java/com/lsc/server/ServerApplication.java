package com.lsc.server;

import com.lsc.server.services.CNTKService;
import com.lsc.server.services.CNTKServiceImpl;
import com.lsc.server.services.TestService;
import com.lsc.server.services.TestServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean("test")
	public TestService testService() { return new TestServiceImpl(); }

	@Bean("cntk")
	public CNTKService cntkService() { return new CNTKServiceImpl(); }

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}
}
