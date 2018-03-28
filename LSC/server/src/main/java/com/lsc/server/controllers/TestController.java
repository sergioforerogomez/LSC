package com.lsc.server.controllers;

import com.lsc.server.entities.TestDTO;
import com.lsc.server.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {
    private TestService testService;

    @Autowired
    public TestController(@Qualifier("test") TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public List<TestDTO> getTest() {
        return testService.getTests();
    }

    @PostMapping("/test")
    public TestDTO postTest(@RequestBody TestDTO testDTO) { return testService.postTest(testDTO); }
}
