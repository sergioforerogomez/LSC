package com.lsc.users.repositories;

import com.lsc.users.entities.AchievementEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AchievementRepository extends MongoRepository<AchievementEntity, String> {
}
