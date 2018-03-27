package com.lsc.server.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class Test {
    @RequestMapping(value = "/test", method = GET)
    public String getTest() {
        return "Hello world!";
    }
}
