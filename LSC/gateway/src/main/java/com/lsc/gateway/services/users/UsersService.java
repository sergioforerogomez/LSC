package com.lsc.gateway.services.users;

import com.lsc.gateway.dtos.users.AchievementDTO;
import com.lsc.gateway.dtos.users.LoginInputDTO;
import com.lsc.gateway.dtos.users.ProfileInputDTO;
import com.lsc.gateway.dtos.users.RegisterDTO;

import java.util.List;

public interface UsersService {
    Object postRegister(RegisterDTO registerDTO);

    Object postLogin(LoginInputDTO loginInputDTO);

    Object getProfileById(String profileId);

    Object putProfileById(String profileId, ProfileInputDTO profileInputDTO);

    Object getAchievementById(String achievementId);

    Object getAchievements();

    Object postUserProfile(List<ProfileInputDTO> profileInputDTOS);

    Object postUserAchievement(List<AchievementDTO> achievementDTOS);
}
