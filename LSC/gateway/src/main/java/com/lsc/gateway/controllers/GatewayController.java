package com.lsc.gateway.controllers;

import com.lsc.gateway.dtos.common.LessonDTO;
import com.lsc.gateway.dtos.common.LevelDTO;
import com.lsc.gateway.dtos.dictionary.WordDTO;
import com.lsc.gateway.dtos.users.AchievementDTO;
import com.lsc.gateway.dtos.users.LoginInputDTO;
import com.lsc.gateway.dtos.users.ProfileInputDTO;
import com.lsc.gateway.dtos.users.RegisterDTO;
import com.lsc.gateway.services.common.CommonService;
import com.lsc.gateway.services.dictionary.DictionaryService;
import com.lsc.gateway.services.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
public class GatewayController {
    private UsersService usersService;
    private CommonService commonService;
    private DictionaryService dictionaryService;

    @Autowired
    public GatewayController(@Qualifier("users") UsersService usersService,
                             @Qualifier("common") CommonService commonService,
                             @Qualifier("dictionary") DictionaryService dictionaryService) {
        this.usersService = usersService;
        this.commonService = commonService;
        this.dictionaryService = dictionaryService;
    }

//    Users

    @PostMapping("/user")
    public Object postRegister(@RequestBody RegisterDTO registerDTO) {
        return this.usersService.postRegister(registerDTO);
    }

    @PostMapping("/login")
    public Object postLogin(@RequestBody LoginInputDTO loginInputDTO) {
        return this.usersService.postLogin(loginInputDTO);
    }

    @GetMapping("/profile/{profileId}")
    public Object getProfileById(@PathVariable String profileId) {
        return this.usersService.getProfileById(profileId);
    }

    @PutMapping("/profile/{profileId}")
    public Object putProfileById(@PathVariable String profileId, @RequestBody ProfileInputDTO profileInputDTO) {
        return this.usersService.putProfileById(profileId, profileInputDTO);
    }

    @GetMapping("/achievement/{achievementId}")
    public Object getAchievementById(@PathVariable String achievementId) {
        return this.usersService.getAchievementById(achievementId);
    }

    @GetMapping("/achievement")
    public Object getAchievements() {
        return this.usersService.getAchievements();
    }

    @PostMapping("/user/profile")
    public Object postUserAProfile(@RequestBody List<ProfileInputDTO> profileInputDTOS) {
        return this.usersService.postUserProfile(profileInputDTOS);
    }

    @PostMapping("/user/achievement")
    public Object postUserAchievement(@RequestBody List<AchievementDTO> achievementDTOS) {
        return this.usersService.postUserAchievement(achievementDTOS);
    }

    @GetMapping("/token")
    public Object getToken() {
        return this.usersService.getToken();
    }

//    Common

    @GetMapping("/level")
    public Object getLevels() {
        return this.commonService.getLevels();
    }

    @GetMapping("/lesson/{levelId}")
    public Object getLessonsByLevelId(@PathVariable String levelId) {
        return this.commonService.lessonsByLevelId(levelId);
    }

    @GetMapping("/practice/{lessonId}")
    public Object getPracticeByLessonId(@PathVariable String lessonId) {
        return this.commonService.getPracticeByLessonId(lessonId);
    }

    @PostMapping("/cntk/{tag}")
    public Object postCNTK(@PathVariable String tag, @RequestPart("file") MultipartFile file) {
        return this.commonService.postCNTK(tag, file);
    }

    @PostMapping("/common/level")
    public Object postCommonLevel(@RequestBody List<LevelDTO> levelsDTO) {
        return this.commonService.postCommonLevel(levelsDTO);
    }

    @PostMapping("/common/lesson")
    public Object postCommonLesson(@RequestBody List<LessonDTO> lessonsDTO) {
        return this.commonService.postCommonLesson(lessonsDTO);
    }

//    Dictionary

    @GetMapping("/word")
    public Object getWords() {
        return this.dictionaryService.getWords();
    }

    @GetMapping("/word/{word}")
    public Object getWordByWord(@PathVariable String word) {
        return this.dictionaryService.getWordByWord(word);
    }

    @PostMapping("/video")
    public Object postVideo(@RequestPart("videoFile") MultipartFile videoFile) {
        return this.dictionaryService.postVideo(videoFile);
    }

    @PostMapping("/picture")
    public Object postPicture(@RequestPart("pictureFile") MultipartFile pictureFile) {
        return this.dictionaryService.postPicture(pictureFile);
    }

    @PostMapping("/word")
    public Object postWord(@RequestBody WordDTO wordDTO) {
        return this.dictionaryService.postWord(wordDTO);
    }

    @PutMapping("/word/{word}")
    public Object postWordByWord(@PathVariable String word, @RequestBody WordDTO wordDTO) {
        return this.dictionaryService.putWordByWord(word, wordDTO);
    }

    @DeleteMapping("/word/{word}")
    public Object deleteWordByWord(@PathVariable String word) {
        return this.dictionaryService.deleteWordByWord(word);
    }

    @DeleteMapping("/video/{name}")
    public Object deleteVideoByName(@PathVariable String name) {
        return this.dictionaryService.deleteVideoByName(name);
    }

    @DeleteMapping("/picture/{name}")
    public Object deletePictureByName(@PathVariable String name) {
        return this.dictionaryService.deletePictureByName(name);
    }

    @PostMapping("/dictionary")
    public Object postDictionary(@RequestBody List<WordDTO> wordDTOS) {
        return this.dictionaryService.postDictionary(wordDTOS);
    }
}
