package com.lsc.media;

import com.lsc.media.services.MediaService;
import com.lsc.media.services.MediaServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaApplication.class, args);
    }

    @Bean("media")
    public MediaService mediaService() {
        return new MediaServiceImpl();
    }
}
