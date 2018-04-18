package com.lsc.gateway;

import com.lsc.gateway.services.common.CommonService;
import com.lsc.gateway.services.common.CommonServiceImpl;
import com.lsc.gateway.services.dictionary.DictionaryService;
import com.lsc.gateway.services.dictionary.DictionaryServiceImpl;
import com.lsc.gateway.services.users.UsersService;
import com.lsc.gateway.services.users.UsersServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean("users")
    public UsersService usersService() {
        return new UsersServiceImpl();
    }

    @Bean("common")
    public CommonService commonService() {
        return new CommonServiceImpl();
    }

    @Bean("dictionary")
    public DictionaryService dictionaryService() {
        return new DictionaryServiceImpl();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
