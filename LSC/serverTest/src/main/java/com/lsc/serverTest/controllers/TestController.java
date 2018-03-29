package com.lsc.serverTest.controllers;

import com.lsc.serverTest.dtos.TestDTO;
import com.lsc.serverTest.services.TestService;
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
        return this.testService.getTests();
    }

    @PostMapping("/test")
    public TestDTO postTest(@RequestBody TestDTO testDTO) { return this.testService.postTest(testDTO); }
}
