package com.lsc.server.controllers;

import com.lsc.server.dtos.CNTKResponseDTO;
import com.lsc.server.dtos.TestDTO;
import com.lsc.server.services.CNTKService;
import com.lsc.server.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class TestController {
    private TestService testService;
    private CNTKService cntkService;

    @Autowired
    public TestController(@Qualifier("test") TestService testService, @Qualifier("cntk") CNTKService cntkService) {
        this.testService = testService;
        this.cntkService = cntkService;
    }

    @GetMapping("/test")
    public List<TestDTO> getTest() {
        return this.testService.getTests();
    }

    @PostMapping("/test")
    public TestDTO postTest(@RequestBody TestDTO testDTO) { return this.testService.postTest(testDTO); }

    @PostMapping("/cntk")
    public CNTKResponseDTO postCNTK(@RequestPart("file") MultipartFile multipartFile) { return this.cntkService.postCNTK(multipartFile); }
}
