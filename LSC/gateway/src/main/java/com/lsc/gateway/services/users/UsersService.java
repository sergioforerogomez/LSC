package com.lsc.gateway.services.users;

import com.lsc.gateway.dtos.ErrorDTO;
import com.lsc.gateway.dtos.users.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UsersService {
    LoginOutputDTO postRegister(RegisterInputDTO registerInputDTO);

    LoginOutputDTO postLogin(LoginInputDTO loginInputDTO);

    ErrorDTO postLogout();

    ProfileOutputDTO getProfileById(String profileId);

    ProfileOutputDTO putProfileById(String profileId, ProfileInputDTO profileInputDTO);

    AchievementOutputDTO getAchievementById(String achievementId);

    List<String> getAchievements();
}
