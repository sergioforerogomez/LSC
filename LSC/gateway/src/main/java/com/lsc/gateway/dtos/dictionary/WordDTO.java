package com.lsc.gateway.dtos.dictionary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class WordDTO {
    private String word;
    private String video;
    private String level;
    private String lesson;
}
