package com.lsc.common.repositories;

import com.lsc.common.entities.LevelEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LevelRepository extends MongoRepository<LevelEntity, String> {
}
