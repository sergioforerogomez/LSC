package com.lsc.gateway.services.common;

import com.lsc.gateway.dtos.common.LessonDTO;
import com.lsc.gateway.dtos.common.LevelDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommonService {
    Object getLevels();

    Object lessonsByLevelId(String levelId);

    Object getPracticeByLessonId(String lessonId);

    Object postCNTK(String tag, MultipartFile file);

    Object postCommonLevel(List<LevelDTO> levelsInputDTO);

    Object postCommonLesson(List<LessonDTO> levelsInputDTO);
}
