package com.lsc.gateway;

import com.lsc.gateway.services.common.CommonService;
import com.lsc.gateway.services.common.CommonServiceImpl;
import com.lsc.gateway.services.dictionary.DictionaryService;
import com.lsc.gateway.services.dictionary.DictionaryServiceImpl;
import com.lsc.gateway.services.games.GamesService;
import com.lsc.gateway.services.games.GamesServiceImpl;
import com.lsc.gateway.services.media.MediaService;
import com.lsc.gateway.services.media.MediaServiceImpl;
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
	public UsersService usersService() { return new UsersServiceImpl();	}

	@Bean("common")
	public CommonService commonService() { return new CommonServiceImpl(); }

	@Bean("games")
	public GamesService gamesService() { return new GamesServiceImpl();	}

	@Bean("dictionary")
	public DictionaryService dictionaryService() { return new DictionaryServiceImpl(); }

	@Bean("media")
	public MediaService mediaService() { return new MediaServiceImpl();	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}
}
