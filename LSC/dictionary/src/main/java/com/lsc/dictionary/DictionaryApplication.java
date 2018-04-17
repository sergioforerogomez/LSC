package com.lsc.dictionary;

import com.lsc.dictionary.services.DictionaryService;
import com.lsc.dictionary.services.DictionaryServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DictionaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictionaryApplication.class, args);
    }

    @Bean("dictionary")
    public DictionaryService dictionaryService() {
        return new DictionaryServiceImpl();
    }
}
