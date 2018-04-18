package com.lsc.common.services;

import com.lsc.common.dtos.LessonDTO;
import com.lsc.common.dtos.LevelDTO;
import com.lsc.common.dtos.WordDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommonService {
    ResponseEntity<Object> getLevels();

    ResponseEntity<Object> getLessonsByLevelId(String levelId);

    ResponseEntity<Object> postPracticeByLessonId(String practiceId, List<WordDTO> wordDTOS);

    ResponseEntity<Object> postCNTK(String tag, MultipartFile file);

    ResponseEntity<Object> postCommonLevel(List<LevelDTO> levelDTOS);

    ResponseEntity<Object> postCommonLesson(List<LessonDTO> lessonDTOS);
}
