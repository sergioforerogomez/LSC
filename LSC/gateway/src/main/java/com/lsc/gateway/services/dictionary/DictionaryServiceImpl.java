package com.lsc.gateway.services.dictionary;

import com.lsc.gateway.dtos.dictionary.WordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoint.url.dictionary}")
    private String url;

    @Override
    public Object getWords() {
        String url = this.url + "/word";
        return restTemplate.getForObject(url, Object.class);
    }

    @Override
    public Object getWordByWord(String word) {
        String url = this.url + "/word/{word}";
        Map<String, Object> params = new HashMap<>();
        params.put("word", word);
        return restTemplate.getForObject(url, Object.class, params);
    }

    @Override
    public Object postVideo(MultipartFile videoFile) {
        String url = this.url + "/video";
        String videoUrl = "";
        try {
            MultiValueMap<String, ByteArrayResource> multiValueMap = new LinkedMultiValueMap<>();
            ByteArrayResource resource = new ByteArrayResource(videoFile.getBytes()) {
                @Override
                public String getFilename() {
                    return videoFile.getOriginalFilename();
                }
            };
            multiValueMap.add("videoFile", resource);
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<MultiValueMap<String, ByteArrayResource>> httpEntity = new HttpEntity<>(multiValueMap, requestHeaders);
            videoUrl = restTemplate.postForObject(url, httpEntity, String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoUrl;
    }

    @Override
    public Object postPicture(MultipartFile pictureFile) {
        String url = this.url + "/picture";
        String pictureUrl = "";
        try {
            MultiValueMap<String, ByteArrayResource> multiValueMap = new LinkedMultiValueMap<>();
            ByteArrayResource resource = new ByteArrayResource(pictureFile.getBytes()) {
                @Override
                public String getFilename() {
                    return pictureFile.getOriginalFilename();
                }
            };
            multiValueMap.add("pictureFile", resource);
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<MultiValueMap<String, ByteArrayResource>> httpEntity = new HttpEntity<>(multiValueMap, requestHeaders);
            pictureUrl = restTemplate.postForObject(url, httpEntity, String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pictureUrl;
    }

    @Override
    public Object postWord(WordDTO wordDTO) {
        String url = this.url + "/word";
        return this.restTemplate.postForObject(url, wordDTO, WordDTO.class);
    }

    @Override
    public Object putWordByWord(String word, WordDTO wordDTO) {
        String url = this.url + "/word/{word}";
        HttpEntity<WordDTO> wordDTOHttpEntity = new HttpEntity<>(wordDTO);
        Map<String, Object> params = new HashMap<>();
        params.put("word", word);
        return this.restTemplate.exchange(url, HttpMethod.PUT, wordDTOHttpEntity, Object.class, params).getBody();
    }

    @Override
    public Object deleteWordByWord(String word) {
        String url = this.url + "/word/{word}";
        Map<String, Object> params = new HashMap<>();
        params.put("word", word);
        return this.restTemplate.exchange(url, HttpMethod.DELETE, null, Object.class, params).getBody();
    }

    @Override
    public Object postDictionary(List<WordDTO> wordDTOS) {
        String url = this.url + "/dictionary";
        return this.restTemplate.postForObject(url, wordDTOS, Object.class);
    }
}
