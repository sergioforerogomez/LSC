package com.lsc.users.repositories;

import com.lsc.users.entities.ProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<ProfileEntity, String> {
    ProfileEntity findByEmail(String email);
}
