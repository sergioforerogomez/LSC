package com.lsc.dictionary.controllers;

import com.lsc.dictionary.dtos.WordDTO;
import com.lsc.dictionary.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
public class DictionaryController {
    private DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(@Qualifier("dictionary") DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/word")
    public ResponseEntity<Object> getWords() {
        return this.dictionaryService.getWords();
    }

    @GetMapping("/word/{word}")
    public ResponseEntity<Object> getWordByWord(@PathVariable String word) {
        return this.dictionaryService.getWordByWord(word);
    }

    @PostMapping("/video")
    public ResponseEntity<Object> postVideo(@RequestPart("videoFile") MultipartFile videoFile) {
        return this.dictionaryService.postVideo(videoFile);
    }

    @PostMapping("/picture")
    public ResponseEntity<Object> postPicture(@RequestPart("pictureFile") MultipartFile pictureFile) {
        return this.dictionaryService.postPicture(pictureFile);
    }

    @PostMapping("/word")
    public ResponseEntity<Object> postWord(@RequestBody WordDTO wordDTO) {
        return this.dictionaryService.postWord(wordDTO);
    }

    @PutMapping("/word/{word}")
    public ResponseEntity<Object> postWordByWord(@PathVariable String word, @RequestBody WordDTO wordDTO) {
        return this.dictionaryService.putWordByWord(word, wordDTO);
    }

    @DeleteMapping("/word/{word}")
    public ResponseEntity<Object> deleteWordByWord(@PathVariable String word) {
        return this.dictionaryService.deleteWordByWord(word);
    }

    @DeleteMapping("/video/{name}")
    public ResponseEntity<Object> deleteVideoByName(@PathVariable String name) {
        return this.dictionaryService.deleteVideoByName(name);
    }

    @DeleteMapping("/picture/{name}")
    public ResponseEntity<Object> deletePictureByName(@PathVariable String name) {
        return this.dictionaryService.deletePictureByName(name);
    }

    @PostMapping("/dictionary")
    public ResponseEntity<Object> postDictionary(@RequestBody List<WordDTO> wordDTOS) {
        return this.dictionaryService.postDictionary(wordDTOS);
    }
}
