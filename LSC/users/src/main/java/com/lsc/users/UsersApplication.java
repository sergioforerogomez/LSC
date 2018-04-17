package com.lsc.users;

import com.lsc.users.services.UsersService;
import com.lsc.users.services.UsersServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

    @Bean("users")
    public UsersService usersService() {
        return new UsersServiceImpl();
    }
}
