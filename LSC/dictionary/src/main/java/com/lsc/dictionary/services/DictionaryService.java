package com.lsc.dictionary.services;

import com.lsc.dictionary.dtos.WordDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DictionaryService {
    ResponseEntity<Object> getWords();

    ResponseEntity<Object> getWordByWord(String word);

    ResponseEntity<Object> postWord(WordDTO wordDTO);

    ResponseEntity<Object> postVideo(MultipartFile videoFile);

    ResponseEntity<Object> postPicture(MultipartFile pictureFile);

    ResponseEntity<Object> putWordByWord(String word, WordDTO wordDTO);

    ResponseEntity<Object> deleteWordByWord(String word);

    ResponseEntity<Object> postDictionary(List<WordDTO> wordDTOS);
}
