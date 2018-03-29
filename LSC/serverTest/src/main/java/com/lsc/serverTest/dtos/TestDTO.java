package com.lsc.serverTest.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TestDTO {
    private String test;
}
