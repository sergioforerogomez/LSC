package com.lsc.gateway.controllers;

import com.lsc.gateway.dtos.ErrorDTO;
import com.lsc.gateway.dtos.common.LevelOutputDTO;
import com.lsc.gateway.dtos.common.PracticeOutputDTO;
import com.lsc.gateway.dtos.dictionary.WordInputDTO;
import com.lsc.gateway.dtos.dictionary.WordOutputDTO;
import com.lsc.gateway.dtos.games.PracticeSchemaInputDTO;
import com.lsc.gateway.dtos.games.PracticeSchemaOutputDTO;
import com.lsc.gateway.dtos.media.PictureInputDTO;
import com.lsc.gateway.dtos.media.PictureOutputDTO;
import com.lsc.gateway.dtos.media.VideoInputDTO;
import com.lsc.gateway.dtos.media.VideoOutputDTO;
import com.lsc.gateway.dtos.users.*;
import com.lsc.gateway.services.common.CommonService;
import com.lsc.gateway.services.dictionary.DictionaryService;
import com.lsc.gateway.services.games.GamesService;
import com.lsc.gateway.services.media.MediaService;
import com.lsc.gateway.services.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GatewayController {
    private UsersService usersService;
    private CommonService commonService;
    private GamesService gamesService;
    private DictionaryService dictionaryService;
    private MediaService mediaService;

    @Autowired
    public GatewayController(@Qualifier("users") UsersService usersService, @Qualifier("common") CommonService commonService,
                             @Qualifier("games") GamesService gamesService, @Qualifier("dictionary") DictionaryService dictionaryService,
                             @Qualifier("media") MediaService mediaService) {
        this.usersService = usersService;
        this.commonService = commonService;
        this.gamesService = gamesService;
        this.dictionaryService = dictionaryService;
        this.mediaService = mediaService;
    }

//    Users

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

//    Common

    @GetMapping("/level/{levelId}")
    public LevelOutputDTO getLevelById(@PathVariable String levelId) {
        return this.commonService.getLevelById(levelId);
    }

    @GetMapping("/level")
    public List<String> getLevels() {
        return this.commonService.getLevels();
    }

    @GetMapping("/practice/{practiceId}")
    public PracticeOutputDTO getPracticeById(@PathVariable String practiceId) {
        return this.commonService.getPracticeById(practiceId);
    }

//    Games

    @GetMapping("/practice/{code}")
    public PracticeSchemaOutputDTO getPracticeSchemaByCode(@PathVariable String code) {
        return this.gamesService.getPracticeSchemaByCode(code);
    }

    @GetMapping("/practice/schema")
    public List<String> getPracticeScchemas() {
        return this.gamesService.getPracticeSchemas();
    }

    @PostMapping("/practice/schema")
    public PracticeSchemaOutputDTO postPracticeSchema(@RequestBody PracticeSchemaInputDTO practiceSchemaDTO) {
        return this.gamesService.postPracticeSchema(practiceSchemaDTO);
    }

    @DeleteMapping("/practice/schema/{code}")
    public void deletePracticeSchema(@PathVariable String code) {
        this.gamesService.deletePracticeSchema(code);
    }

//    Dictionary

    @GetMapping("/word/{concept}")
    public List<String> getWordsByConcept(@PathVariable String concept) {
        return this.dictionaryService.getWordsByConcept(concept);
    }

    @GetMapping("/word")
    public List<String> getWords() {
        return this.dictionaryService.getWords();
    }

    @PostMapping("/word")
    public WordOutputDTO postWord(@RequestBody WordInputDTO wordInputDTO) {
        return this.dictionaryService.postWord(wordInputDTO);
    }

    @DeleteMapping("/word/{word}")
    public void deleteWord(@PathVariable String word) {
        this.dictionaryService.deleteWord(word);
    }

//    Media

    @GetMapping("/video/{word}")
    public String getVideoByWord(@PathVariable String word) {
        return this.mediaService.getVideoByWord(word);
    }

    @PostMapping("/video")
    public VideoOutputDTO postVideo(@RequestBody VideoInputDTO videoInputDTO) {
        return this.mediaService.postVideo(videoInputDTO);
    }

    @PostMapping("/picture")
    public PictureOutputDTO postPicture(@RequestBody PictureInputDTO pictureInputDTO) {
        return this.mediaService.postPicture(pictureInputDTO);
    }

    @DeleteMapping("/picture/{word}")
    public void deletePicture(@PathVariable String word) {
        this.mediaService.deletePicture(word);
    }
}
