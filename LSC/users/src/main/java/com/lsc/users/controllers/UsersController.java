package com.lsc.users.controllers;

import com.lsc.users.dtos.AchievementDTO;
import com.lsc.users.dtos.LoginInputDTO;
import com.lsc.users.dtos.ProfileInputDTO;
import com.lsc.users.dtos.RegisterDTO;
import com.lsc.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> postRegister(@RequestBody RegisterDTO registerDTO) {
        return this.usersService.postRegister(registerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> postLogin(@RequestBody LoginInputDTO loginInputDTO) {
        return this.usersService.postLogin(loginInputDTO);
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<Object> getProfileById(@PathVariable String profileId) {
        return this.usersService.getProfileById(profileId);
    }

    @PutMapping("/profile/{profileId}")
    public ResponseEntity<Object> putProfileById(@PathVariable String profileId, @RequestBody ProfileInputDTO profileInputDTO) {
        return this.usersService.putProfileById(profileId, profileInputDTO);
    }

    @GetMapping("/achievement/{achievementId}")
    public ResponseEntity<Object> getAchievementById(@PathVariable String achievementId) {
        return this.usersService.getAchievementById(achievementId);
    }

    @GetMapping("/achievement")
    public ResponseEntity<Object> getAchievements() {
        return this.usersService.getAchievements();
    }

    @PostMapping("/user/profile")
    public ResponseEntity<Object> postUserProfile(@RequestBody List<ProfileInputDTO> profileInputDTOS) {
        return this.usersService.postUserProfile(profileInputDTOS);
    }

    @PostMapping("/user/achievement")
    public ResponseEntity<Object> postUserAchievement(@RequestBody List<AchievementDTO> achievementDTOS) {
        return this.usersService.postUserAchievement(achievementDTOS);
    }
}
