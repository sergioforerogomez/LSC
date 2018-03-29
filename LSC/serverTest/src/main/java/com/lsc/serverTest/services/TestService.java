package com.lsc.serverTest.services;

import com.lsc.serverTest.dtos.TestDTO;

import java.util.List;

public interface TestService {
    List<TestDTO> getTests();
    TestDTO postTest(TestDTO testDTO);
}
