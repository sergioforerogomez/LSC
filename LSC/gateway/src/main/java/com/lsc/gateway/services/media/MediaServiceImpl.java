package com.lsc.gateway.services.media;

import com.lsc.gateway.dtos.media.PictureInputDTO;
import com.lsc.gateway.dtos.media.PictureOutputDTO;
import com.lsc.gateway.dtos.media.VideoInputDTO;
import com.lsc.gateway.dtos.media.VideoOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class MediaServiceImpl implements MediaService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoint.url.media}")
    private String url;

    @Override
    public String getVideoByWord(String word) {
        String url = this.url + "/video/{word}";
        Map<String, Object> params = new HashMap<>();
        params.put("word", word);
        return restTemplate.getForObject(url, String.class, params);
    }

    @Override
    public VideoOutputDTO postVideo(VideoInputDTO videoInputDTO) {
        String url = this.url + "/video";
        return this.restTemplate.postForObject(url, videoInputDTO, VideoOutputDTO.class);
    }

    @Override
    public PictureOutputDTO postPicture(PictureInputDTO pictureInputDTO) {
        String url = this.url + "/picture";
        return this.restTemplate.postForObject(url, pictureInputDTO, PictureOutputDTO.class);
    }

    @Override
    public void deletePicture(String word) {
        String url = this.url + "/picture/{word}";
        Map<String, Object> params = new HashMap<>();
        params.put("word", word);
        restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class, params).getBody();
    }
}
