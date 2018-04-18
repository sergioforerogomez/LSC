package com.lsc.common.repositories;

import com.lsc.common.entities.LessonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LessonRepository extends MongoRepository<LessonEntity, String> {
}
