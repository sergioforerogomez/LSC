package com.lsc.server.services;

import com.lsc.server.entities.TestDTO;
import com.lsc.server.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public List<TestDTO> getTests() {
        return this.testRepository.findAll();
    }

    @Override
    public TestDTO postTest(TestDTO testDTO) {
        testRepository.save(testDTO);
        return testDTO;
    }
}
