package com.lsc.server.services;

import com.lsc.server.dtos.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class TestServiceImpl implements TestService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoint.url}")
    private String url;

    @Override
    public List<TestDTO> getTests() {
        TestDTO[] testDTOS = this.restTemplate.getForObject(url, TestDTO[].class);
        return Arrays.asList(testDTOS);
    }

    @Override
    public TestDTO postTest(TestDTO testDTO) {
        return this.restTemplate.postForObject(url, testDTO, TestDTO.class);
    }
}
