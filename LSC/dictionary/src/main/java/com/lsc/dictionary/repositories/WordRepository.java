package com.lsc.dictionary.repositories;

import com.lsc.dictionary.entities.WordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordRepository extends MongoRepository<WordEntity, String> {
    WordEntity findByWord(String word);
}
