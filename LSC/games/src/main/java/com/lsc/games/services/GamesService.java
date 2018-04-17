package com.lsc.games.services;

import com.lsc.games.dtos.PracticeSchemaInputDTO;
import com.lsc.games.dtos.PracticeSchemaOutputDTO;

import java.util.List;

public interface GamesService {
    PracticeSchemaOutputDTO getPracticeSchemaByCode(String code);

    List<String> getPracticeSchemas();

    PracticeSchemaOutputDTO postPracticeSchema(PracticeSchemaInputDTO practiceSchemaDTO);

    void deletePracticeSchema(String code);
}
