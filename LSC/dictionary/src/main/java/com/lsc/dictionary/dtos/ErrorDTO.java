package com.lsc.dictionary.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ErrorDTO {
    private String errorMessage;
}
