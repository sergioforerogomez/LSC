package com.lsc.games;

import com.lsc.games.services.GamesService;
import com.lsc.games.services.GamesServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GamesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamesApplication.class, args);
    }

    @Bean("games")
    public GamesService gamesService() {
        return new GamesServiceImpl();
    }
}
