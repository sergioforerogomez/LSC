package com.lsc.gateway.services.common;

import com.lsc.gateway.dtos.common.LevelIOutputDTO;
import com.lsc.gateway.dtos.common.PracticeOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonServiceImpl implements CommonService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoint.url.common}")
    private String url;

    @Override
    public LevelIOutputDTO getLevelById(String levelId) {
        String url = this.url + "/level/{levelId}";
        Map<String, Object> params = new HashMap<>();
        params.put("levelId", levelId);
        return restTemplate.getForObject(url, LevelIOutputDTO.class, params);
    }

    @Override
    public List<String> getLevels() {
        String url = this.url + "/level";
        String[] levels = restTemplate.getForObject(url, String[].class);
        return Arrays.asList(levels);
    }

    @Override
    public PracticeOutputDTO getPracticeById(String practiceId) {
        String url = this.url + "/practice/{practiceId}";
        Map<String, Object> params = new HashMap<>();
        params.put("practiceId", practiceId);
        return restTemplate.getForObject(url, PracticeOutputDTO.class, params);
    }
}
