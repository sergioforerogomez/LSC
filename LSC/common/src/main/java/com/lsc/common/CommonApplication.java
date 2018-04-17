package com.lsc.common;

import com.lsc.common.services.CommonService;
import com.lsc.common.services.CommonServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }

    @Bean("common")
    public CommonService commonService() {
        return new CommonServiceImpl();
    }
}
