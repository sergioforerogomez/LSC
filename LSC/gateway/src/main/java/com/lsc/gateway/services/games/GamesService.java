package com.lsc.gateway.services.games;

import com.lsc.gateway.dtos.games.PracticeSchemaInputDTO;
import com.lsc.gateway.dtos.games.PracticeSchemaOutputDTO;

import java.util.List;

public interface GamesService {
    PracticeSchemaOutputDTO getPracticeSchemaByCode(String code);

    List<String> getPracticeSchemas();

    PracticeSchemaOutputDTO postPracticeSchema(PracticeSchemaInputDTO practiceSchemaDTO);

    void deletePracticeSchema(String code);
}
