package com.lsc.cntkTest.services;

import com.lsc.cntkTest.dtos.CNTKResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class CNTKServiceImpl implements CNTKService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoint.url.cntk}")
    private String url;

    @Value("${my.predictionKey}")
    private String predictionKey;

    @Override
    public CNTKResponseDTO postCNTK(MultipartFile file) {
        CNTKResponseDTO cntkResponseDTO = null;
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Prediction-key", this.predictionKey);
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<ByteArrayResource> httpEntity = new HttpEntity<>(new ByteArrayResource(file.getBytes()), httpHeaders);
            cntkResponseDTO = restTemplate.postForObject(this.url, httpEntity, CNTKResponseDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cntkResponseDTO;
    }
}
