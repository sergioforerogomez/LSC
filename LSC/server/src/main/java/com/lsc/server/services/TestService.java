package com.lsc.server.services;

import com.lsc.server.dtos.TestDTO;

import java.util.List;

public interface TestService {
    List<TestDTO> getTests();
    TestDTO postTest(TestDTO testDTO);
}
