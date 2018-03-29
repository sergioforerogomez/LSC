package com.lsc.serverTest.services;

import com.lsc.serverTest.dtos.TestDTO;
import com.lsc.serverTest.entities.TestEntity;
import com.lsc.serverTest.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public List<TestDTO> getTests() {
        List<TestDTO> testDTOS = new ArrayList<>();
        for(TestEntity testEntity : testRepository.findAll()) {
            testDTOS.add(new TestDTO().setTest(testEntity.getTest()));
        }
        return testDTOS;
    }

    @Override
    public TestDTO postTest(TestDTO testDTO) {
        TestEntity testEntity = new TestEntity().setTest(testDTO.getTest());
        testRepository.save(testEntity);
        return testDTO;
    }
}
