package com.lsc.gateway.services.common;

import com.lsc.gateway.dtos.common.LevelOutputDTO;
import com.lsc.gateway.dtos.common.PracticeOutputDTO;

import java.util.List;

public interface CommonService {
    LevelOutputDTO getLevelById(String levelId);

    List<String> getLevels();

    PracticeOutputDTO getPracticeById(String practiceId);
}
