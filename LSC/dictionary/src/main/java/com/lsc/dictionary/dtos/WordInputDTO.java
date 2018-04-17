package com.lsc.dictionary.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WordInputDTO {
    private String word;
    private String concept;
    private String video;
}
