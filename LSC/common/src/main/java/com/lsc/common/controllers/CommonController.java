package com.lsc.common.controllers;

import com.lsc.common.dtos.LessonDTO;
import com.lsc.common.dtos.LevelDTO;
import com.lsc.common.dtos.WordDTO;
import com.lsc.common.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class CommonController {
    private CommonService commonService;

    @Autowired
    public CommonController(@Qualifier("common") CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping("/level")
    public ResponseEntity<Object> getLevels() {
        return this.commonService.getLevels();
    }

    @GetMapping("/lesson/{levelId}")
    public ResponseEntity<Object> getLessonsByLevelId(@PathVariable String levelId) {
        return this.commonService.getLessonsByLevelId(levelId);
    }

    @PostMapping("/practice/{lessonId}")
    public ResponseEntity<Object> postPracticeByLessonId(@PathVariable String lessonId, @RequestBody List<WordDTO> wordDTOS) {
        return this.commonService.postPracticeByLessonId(lessonId, wordDTOS);
    }

    @PostMapping("/cntk/{tag}")
    public ResponseEntity<Object> postCNTK(@PathVariable String tag, @RequestPart("file") MultipartFile file) {
        return this.commonService.postCNTK(tag, file);
    }

    @PostMapping("/common/level")
    public ResponseEntity<Object> postCommonLevel(@RequestBody List<LevelDTO> levelsDTO) {
        return this.commonService.postCommonLevel(levelsDTO);
    }

    @PostMapping("/common/lesson")
    public ResponseEntity<Object> postCommonLesson(@RequestBody List<LessonDTO> lessonsDTO) {
        return this.commonService.postCommonLesson(lessonsDTO);
    }
}
