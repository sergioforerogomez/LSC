package com.lsc.gateway.services.users;

import com.lsc.gateway.dtos.ErrorDTO;
import com.lsc.gateway.dtos.users.*;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersServiceImpl implements UsersService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoint.url.users}")
    private String url;

    @Override
    public LoginOutputDTO postRegister(RegisterInputDTO registerInputDTO) {
        String url = this.url + "/user";
        return this.restTemplate.postForObject(url, registerInputDTO, LoginOutputDTO.class);
    }

    @Override
    public LoginOutputDTO postLogin(LoginInputDTO loginInputDTO) {
        String url = this.url + "/login";
        return this.restTemplate.postForObject(url, loginInputDTO, LoginOutputDTO.class);
    }

    @Override
    public ErrorDTO postLogout() {
        String url = this.url + "/logout";
        return this.restTemplate.postForObject(url, null, ErrorDTO.class);
    }

    @Override
    public ProfileOutputDTO getProfileById(String profileId) {
        String url = this.url + "/profile/{profileId}";
        Map<String, Object> params = new HashMap<>();
        params.put("profileId", profileId);
        return restTemplate.getForObject(url, ProfileOutputDTO.class, params);
    }

    @Override
    public ProfileOutputDTO putProfileById(String profileId, ProfileInputDTO profileInputDTO) {
        String url = this.url + "/profile/{profileId}";
        HttpEntity<ProfileInputDTO> profileInputDTOHttpEntity = new HttpEntity<>(profileInputDTO);
        Map<String, Object> params = new HashMap<>();
        params.put("profileId", profileId);
        return restTemplate.exchange(url, HttpMethod.PUT, profileInputDTOHttpEntity, ProfileOutputDTO.class, params).getBody();
    }

    @Override
    public AchievementOutputDTO getAchievementById(String achievementId) {
        String url = this.url + "/achievement/{achievementId}";
        Map<String, Object> params = new HashMap<>();
        params.put("achievementId", achievementId);
        return restTemplate.getForObject(url, AchievementOutputDTO.class, params);
    }

    @Override
    public List<String> getAchievements() {
        String url = this.url + "/achievement";
        String[] achievements = restTemplate.getForObject(url, String[].class);
        return Arrays.asList(achievements);
    }
}
