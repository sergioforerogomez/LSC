package com.lsc.cntkTest.controllers;

import com.lsc.cntkTest.dtos.CNTKResponseDTO;
import com.lsc.cntkTest.services.CNTKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CNTKController {
    private CNTKService cntkService;

    @Autowired
    public CNTKController(@Qualifier("cntk") CNTKService cntkService) { this.cntkService = cntkService; }

    @PostMapping("/cntk")
    public CNTKResponseDTO postCNTK(@RequestPart("file") MultipartFile file) { return this.cntkService.postCNTK(file); }
}
