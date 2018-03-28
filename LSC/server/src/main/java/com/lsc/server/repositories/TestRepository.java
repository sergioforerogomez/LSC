package com.lsc.server.repositories;

import com.lsc.server.entities.TestDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<TestDTO, String> {
}
