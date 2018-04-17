package com.lsc.dictionary.controllers;

import com.lsc.dictionary.dtos.WordInputDTO;
import com.lsc.dictionary.dtos.WordOutputDTO;
import com.lsc.dictionary.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DictionaryController {
    private DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(@Qualifier("dictionary") DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/word/{concept}")
    public List<String> getWordsByConcept(@PathVariable String concept) {
        return this.dictionaryService.getWordsByConcept(concept);
    }

    @GetMapping("/word")
    public List<String> getWords() {
        return this.dictionaryService.getWords();
    }

    @PostMapping("/word")
    public WordOutputDTO postWord(@RequestBody WordInputDTO wordInputDTO) {
        return this.dictionaryService.postWord(wordInputDTO);
    }

    @DeleteMapping("/word/{word}")
    public void deleteWord(@PathVariable String word) {
        this.dictionaryService.deleteWord(word);
    }
}
