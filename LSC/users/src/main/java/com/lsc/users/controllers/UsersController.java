package com.lsc.users.controllers;

import com.lsc.users.dtos.*;
import com.lsc.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {
    private UsersService usersService;

    @Autowired
    public UsersController(@Qualifier("users") UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/user")
    public LoginOutputDTO postRegister(@RequestBody RegisterInputDTO registerInputDTO) {
        return this.usersService.postRegister(registerInputDTO);
    }

    @PostMapping("/login")
    public LoginOutputDTO postLogin(@RequestBody LoginInputDTO loginInputDTO) {
        return this.usersService.postLogin(loginInputDTO);
    }

    @PostMapping("/logout")
    public ErrorDTO postLogout() {
        return this.usersService.postLogout();
    }

    @GetMapping("/profile/{profileId}")
    public ProfileOutputDTO getProfileById(@PathVariable String profileId) {
        return this.usersService.getProfileById(profileId);
    }

    @PutMapping("/profile/{profileId}")
    public ProfileOutputDTO putProfileById(@PathVariable String profileId, @RequestBody ProfileInputDTO profileInputDTO) {
        return this.usersService.putProfileById(profileId, profileInputDTO);
    }

    @GetMapping("/achievement/{achievementId}")
    public AchievementOutputDTO getAchievementById(@PathVariable String achievementId) {
        return this.usersService.getAchievementById(achievementId);
    }

    @GetMapping("/achievement")
    public List<String> getAchievements() {
        return this.usersService.getAchievements();
    }
}
