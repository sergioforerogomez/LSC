package com.lsc.gateway.services.common;

import com.lsc.gateway.dtos.common.CNTKOutputDTO;
import com.lsc.gateway.dtos.common.LessonDTO;
import com.lsc.gateway.dtos.common.LevelDTO;
import com.lsc.gateway.dtos.dictionary.WordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonServiceImpl implements CommonService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoint.url.common}")
    private String url;

    @Value("${endpoint.url.dictionary}")
    private String dictionaryUrl;

    @Override
    public Object getLevels() {
        String url = this.url + "/level";
        return restTemplate.getForObject(url, Object.class);
    }

    @Override
    public Object lessonsByLevelId(String levelId) {
        String url = this.url + "/lesson/{levelId}";
        Map<String, Object> params = new HashMap<>();
        params.put("levelId", levelId);
        return restTemplate.getForObject(url, Object.class, params);
    }

    @Override
    public Object getPracticeByLessonId(String lessonId) {
        String url = this.url + "/practice/{lessonId}";
        Map<String, Object> params = new HashMap<>();
        params.put("lessonId", lessonId);
        return restTemplate.postForObject(url, restTemplate.getForObject(dictionaryUrl + "/word", WordDTO[].class), Object.class, params);
    }

    @Override
    public Object postCNTK(String tag, MultipartFile multipartFile) {
        String url = this.url + "/cntk/{tag}";
        CNTKOutputDTO cntkOutputDTO = null;
        try {
            MultiValueMap<String, ByteArrayResource> multiValueMap = new LinkedMultiValueMap<>();
            ByteArrayResource contentsAsResource = new ByteArrayResource(multipartFile.getBytes()) {
                @Override
                public String getFilename() {
                    return multipartFile.getOriginalFilename();
                }
            };
            multiValueMap.add("file", contentsAsResource);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<MultiValueMap<String, ByteArrayResource>> httpEntity = new HttpEntity<>(multiValueMap, httpHeaders);
            Map<String, Object> params = new HashMap<>();
            params.put("tag", tag);
            cntkOutputDTO = this.restTemplate.postForObject(url, httpEntity, CNTKOutputDTO.class, params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cntkOutputDTO;
    }

    @Override
    public Object postCommonLevel(List<LevelDTO> levelDTOS) {
        String url = this.url + "/common/level";
        return this.restTemplate.postForObject(url, levelDTOS, Object.class);
    }

    @Override
    public Object postCommonLesson(List<LessonDTO> lessonDTOS) {
        String url = this.url + "/common/lesson";
        return this.restTemplate.postForObject(url, lessonDTOS, Object.class);
    }
}
