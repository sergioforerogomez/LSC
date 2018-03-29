package com.lsc.serverTest.repositories;

import com.lsc.serverTest.entities.TestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<TestEntity, String> {
}
