package com.lsc.dictionary.services;

import com.lsc.dictionary.dtos.WordInputDTO;
import com.lsc.dictionary.dtos.WordOutputDTO;

import java.util.List;

public interface DictionaryService {
    List<String> getWordsByConcept(String concept);

    List<String> getWords();

    WordOutputDTO postWord(WordInputDTO wordInputDTO);

    void deleteWord(String word);
}
