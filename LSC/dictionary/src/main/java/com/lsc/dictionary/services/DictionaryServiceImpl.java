package com.lsc.dictionary.services;

import com.lsc.dictionary.dtos.WordInputDTO;
import com.lsc.dictionary.dtos.WordOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class DictionaryServiceImpl implements DictionaryService {
    @Override
    public List<String> getWordsByConcept(String concept) {
        return new ArrayList<>();
    }

    @Override
    public List<String> getWords() {
        return new ArrayList<>();
    }

    @Override
    public WordOutputDTO postWord(WordInputDTO wordInputDTO) {
        return new WordOutputDTO();
    }

    @Override
    public void deleteWord(String word) {

    }
}
