package com.lsc.games.controllers;

import com.lsc.games.dtos.PracticeSchemaInputDTO;
import com.lsc.games.dtos.PracticeSchemaOutputDTO;
import com.lsc.games.services.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GamesController {
    private GamesService gamesService;

    @Autowired
    public GamesController(@Qualifier("games") GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @GetMapping("/practice/{code}")
    public PracticeSchemaOutputDTO getPracticeSchemaByCode(@PathVariable String code) {
        return this.gamesService.getPracticeSchemaByCode(code);
    }

    @GetMapping("/practice/schema")
    public List<String> getPracticeScchemas() {
        return this.gamesService.getPracticeSchemas();
    }

    @PostMapping("/practice/schema")
    public PracticeSchemaOutputDTO postPracticeSchema(@RequestBody PracticeSchemaInputDTO practiceSchemaDTO) {
        return this.gamesService.postPracticeSchema(practiceSchemaDTO);
    }

    @DeleteMapping("/practice/schema/{code}")
    public void deletePracticeSchema(@PathVariable String code) {
        this.gamesService.deletePracticeSchema(code);
    }
}
