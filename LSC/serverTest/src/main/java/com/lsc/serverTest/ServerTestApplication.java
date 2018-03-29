package com.lsc.serverTest;

import com.lsc.serverTest.services.TestService;
import com.lsc.serverTest.services.TestServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerTestApplication.class, args);
	}

	@Bean("test")
	public TestService testService() { return new TestServiceImpl(); };
}
