package com.lsc.server;

import com.lsc.server.services.TestService;
import com.lsc.server.services.TestServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean("test")
	public TestService testService() { return new TestServiceImpl(); };
}
