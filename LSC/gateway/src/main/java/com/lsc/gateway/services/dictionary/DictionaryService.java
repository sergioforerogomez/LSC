package com.lsc.gateway.services.dictionary;

import com.lsc.gateway.dtos.dictionary.WordDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DictionaryService {
    Object getWords();

    Object getWordByWord(String word);

    Object postVideo(MultipartFile videoFile);

    Object postPicture(MultipartFile pictureFile);

    Object postWord(WordDTO wordDTO);

    Object putWordByWord(String word, WordDTO wordDTO);

    Object deleteWordByWord(String word);

    Object postDictionary(List<WordDTO> wordDTOS);
}
