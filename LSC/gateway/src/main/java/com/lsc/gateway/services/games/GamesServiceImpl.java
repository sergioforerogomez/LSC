package com.lsc.gateway.services.games;

import com.lsc.gateway.dtos.games.PracticeSchemaInputDTO;
import com.lsc.gateway.dtos.games.PracticeSchemaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamesServiceImpl implements GamesService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoint.url.games}")
    private String url;


    @Override
    public PracticeSchemaOutputDTO getPracticeSchemaByCode(String code) {
        String url = this.url + "/practice/schema/{code}";
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        return restTemplate.getForObject(url, PracticeSchemaOutputDTO.class, params);
    }

    @Override
    public List<String> getPracticeSchemas() {
        String url = this.url + "/practice/schema";
        String[] practiceSchemas = restTemplate.getForObject(url, String[].class);
        return Arrays.asList(practiceSchemas);
    }

    @Override
    public PracticeSchemaOutputDTO postPracticeSchema(PracticeSchemaInputDTO practiceSchemaDTO) {
        String url = this.url + "/practice/schema";
        return this.restTemplate.postForObject(url, practiceSchemaDTO, PracticeSchemaOutputDTO.class);
    }

    @Override
    public void deletePracticeSchema(String code) {
        String url = this.url + "/practice/schema/{code}";
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class, params).getBody();
    }
}
