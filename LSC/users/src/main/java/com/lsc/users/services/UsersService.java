package com.lsc.users.services;

import com.lsc.users.dtos.AchievementDTO;
import com.lsc.users.dtos.LoginInputDTO;
import com.lsc.users.dtos.ProfileInputDTO;
import com.lsc.users.dtos.RegisterDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsersService {
    ResponseEntity<Object> postRegister(RegisterDTO registerDTO);

    ResponseEntity<Object> postLogin(LoginInputDTO loginInputDTO);

    ResponseEntity<Object> getProfileById(String profileId);

    ResponseEntity<Object> putProfileById(String profileId, ProfileInputDTO profileInputDTO);

    ResponseEntity<Object> getAchievementById(String achievementId);

    ResponseEntity<Object> getAchievements();

    ResponseEntity<Object> postUserAchievement(List<AchievementDTO> achievementDTOS);
}
