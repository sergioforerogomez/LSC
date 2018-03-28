package com.lsc.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsc.server.dtos.CNTKResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class CNTKServiceImpl implements CNTKService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoint.url.cntkTest}")
    private String url;

    @Override
    public CNTKResponseDTO postCNTK(MultipartFile multipartFile) {
        CNTKResponseDTO cntkResponseDTO = null;
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            MultiValueMap<String, ByteArrayResource> map = new LinkedMultiValueMap<>();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            ByteArrayResource contentsAsResource = new ByteArrayResource(multipartFile.getBytes()){
                @Override
                public String getFilename(){
                    return multipartFile.getOriginalFilename();
                }
            };
            map.add("file", contentsAsResource);
            HttpEntity<MultiValueMap<String, ByteArrayResource>> httpEntity = new HttpEntity<>(map, httpHeaders);
            cntkResponseDTO = restTemplate.postForObject(this.url, httpEntity, CNTKResponseDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cntkResponseDTO;
    }
}
