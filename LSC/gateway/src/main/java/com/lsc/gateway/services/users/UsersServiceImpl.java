package com.lsc.gateway.services.users;

import com.lsc.gateway.dtos.users.AchievementDTO;
import com.lsc.gateway.dtos.users.LoginInputDTO;
import com.lsc.gateway.dtos.users.ProfileInputDTO;
import com.lsc.gateway.dtos.users.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersServiceImpl implements UsersService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${endpoint.url.users}")
    private String url;

    @Override
    public Object postRegister(RegisterDTO registerDTO) {
        String url = this.url + "/user";
        return this.restTemplate.postForObject(url, registerDTO, Object.class);
    }

    @Override
    public Object postLogin(LoginInputDTO loginInputDTO) {
        String url = this.url + "/login";
        return this.restTemplate.postForObject(url, loginInputDTO, Object.class);
    }

    @Override
    public Object getProfileById(String profileId) {
        String url = this.url + "/profile/{profileId}";
        Map<String, Object> params = new HashMap<>();
        params.put("profileId", profileId);
        return restTemplate.getForObject(url, Object.class, params);
    }

    @Override
    public Object putProfileById(String profileId, ProfileInputDTO profileInputDTO) {
        String url = this.url + "/profile/{profileId}";
        HttpEntity<ProfileInputDTO> profileInputDTOHttpEntity = new HttpEntity<>(profileInputDTO);
        Map<String, Object> params = new HashMap<>();
        params.put("profileId", profileId);
        return restTemplate.exchange(url, HttpMethod.PUT, profileInputDTOHttpEntity, Object.class, params).getBody();
    }

    @Override
    public Object getAchievementById(String achievementId) {
        String url = this.url + "/achievement/{achievementId}";
        Map<String, Object> params = new HashMap<>();
        params.put("achievementId", achievementId);
        return restTemplate.getForObject(url, Object.class, params);
    }

    @Override
    public Object getAchievements() {
        String url = this.url + "/achievement";
        return restTemplate.getForObject(url, Object.class);
    }

    @Override
    public Object postUserAchievement(List<AchievementDTO> achievementDTOS) {
        String url = this.url + "/user/achievement";
        return this.restTemplate.postForObject(url, achievementDTOS, Object.class);
    }
}
