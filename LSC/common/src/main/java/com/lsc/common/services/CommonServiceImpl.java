package com.lsc.common.services;

import com.lsc.common.dtos.LevelOutputDTO;
import com.lsc.common.dtos.PracticeOutputDTO;

import java.util.ArrayList;
import java.util.List;

public class CommonServiceImpl implements CommonService {
    @Override
    public LevelOutputDTO getLevelById(String levelId) {
        return new LevelOutputDTO();
    }

    @Override
    public List<String> getLevels() {
        return new ArrayList<>();
    }

    @Override
    public PracticeOutputDTO getPracticeById(String practiceId) {
        return new PracticeOutputDTO();
    }
}
