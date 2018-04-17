package com.lsc.common.controllers;

import com.lsc.common.dtos.LevelOutputDTO;
import com.lsc.common.dtos.PracticeOutputDTO;
import com.lsc.common.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommonController {
    private CommonService commonService;

    @Autowired
    public CommonController(@Qualifier("common") CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping("/level/{levelId}")
    public LevelOutputDTO getLevelById(@PathVariable String levelId) {
        return this.commonService.getLevelById(levelId);
    }

    @GetMapping("/level")
    public List<String> getLevels() {
        return this.commonService.getLevels();
    }

    @GetMapping("/practice/{practiceId}")
    public PracticeOutputDTO getPracticeById(@PathVariable String practiceId) {
        return this.commonService.getPracticeById(practiceId);
    }
}
