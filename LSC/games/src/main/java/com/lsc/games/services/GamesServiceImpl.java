package com.lsc.games.services;

import com.lsc.games.dtos.PracticeSchemaInputDTO;
import com.lsc.games.dtos.PracticeSchemaOutputDTO;

import java.util.ArrayList;
import java.util.List;

public class GamesServiceImpl implements GamesService {
    @Override
    public PracticeSchemaOutputDTO getPracticeSchemaByCode(String code) {
        return new PracticeSchemaOutputDTO();
    }

    @Override
    public List<String> getPracticeSchemas() {
        return new ArrayList<>();
    }

    @Override
    public PracticeSchemaOutputDTO postPracticeSchema(PracticeSchemaInputDTO practiceSchemaDTO) {
        return new PracticeSchemaOutputDTO();
    }

    @Override
    public void deletePracticeSchema(String code) {

    }
}
