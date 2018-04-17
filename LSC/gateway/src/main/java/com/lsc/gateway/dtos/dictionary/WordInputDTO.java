package com.lsc.gateway.dtos.dictionary;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WordInputDTO {
    private String word;
    private String concept;
    private String video;
}
