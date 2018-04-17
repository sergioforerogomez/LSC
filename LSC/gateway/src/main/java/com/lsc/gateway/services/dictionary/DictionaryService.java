package com.lsc.gateway.services.dictionary;

import com.lsc.gateway.dtos.dictionary.WordInputDTO;
import com.lsc.gateway.dtos.dictionary.WordOutputDTO;

import java.util.List;

public interface DictionaryService {
    List<String> getWordsByConcept(String concept);

    List<String> getWords();

    WordOutputDTO postWord(WordInputDTO wordInputDTO);

    void deleteWord(String word);
}
