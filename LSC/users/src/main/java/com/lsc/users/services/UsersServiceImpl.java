package com.lsc.users.services;

import com.lsc.users.dtos.*;

import java.util.ArrayList;
import java.util.List;

public class UsersServiceImpl implements UsersService {
    @Override
    public LoginOutputDTO postRegister(RegisterInputDTO registerInputDTO) {
        return new LoginOutputDTO();
    }

    @Override
    public LoginOutputDTO postLogin(LoginInputDTO loginInputDTO) {
        return new LoginOutputDTO();
    }

    @Override
    public ErrorDTO postLogout() {
        return new ErrorDTO();
    }

    @Override
    public ProfileOutputDTO getProfileById(String profileId) {
        return new ProfileOutputDTO();
    }

    @Override
    public ProfileOutputDTO putProfileById(String profileId, ProfileInputDTO profileInputDTO) {
        return new ProfileOutputDTO();
    }

    @Override
    public AchievementOutputDTO getAchievementById(String achievementId) {
        return new AchievementOutputDTO();
    }

    @Override
    public List<String> getAchievements() {
        return new ArrayList<>();
    }
}
