package com.lsc.gateway.services.dictionary;

import com.lsc.gateway.dtos.dictionary.WordInputDTO;
import com.lsc.gateway.dtos.dictionary.WordOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoint.url.dictionary}")
    private String url;

    @Override
    public List<String> getWordsByConcept(String concept) {
        String url = this.url + "/word/{concept}";
        Map<String, Object> params = new HashMap<>();
        params.put("concept", concept);
        String[] words = restTemplate.getForObject(url, String[].class, params);
        return Arrays.asList(words);
    }

    @Override
    public List<String> getWords() {
        String url = this.url + "/word";
        String[] words = restTemplate.getForObject(url, String[].class);
        return Arrays.asList(words);
    }

    @Override
    public WordOutputDTO postWord(WordInputDTO wordInputDTO) {
        String url = this.url + "/word";
        return this.restTemplate.postForObject(url, wordInputDTO, WordOutputDTO.class);
    }

    @Override
    public void deleteWord(String word) {
        String url = this.url + "/word";
        Map<String, Object> params = new HashMap<>();
        params.put("word", word);
        restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class, params).getBody();
    }
}
