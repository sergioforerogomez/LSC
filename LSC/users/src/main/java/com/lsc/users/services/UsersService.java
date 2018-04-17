package com.lsc.users.services;

import com.lsc.users.dtos.*;

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
