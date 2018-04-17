package com.lsc.common.services;

import com.lsc.common.dtos.LevelOutputDTO;
import com.lsc.common.dtos.PracticeOutputDTO;

import java.util.List;

public interface CommonService {
    LevelOutputDTO getLevelById(String levelId);

    List<String> getLevels();

    PracticeOutputDTO getPracticeById(String practiceId);
}
