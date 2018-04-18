package com.lsc.dictionary.entities;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
public class WordEntity {
    @Id
    private String wordId;
    private String word;
    private String video;
    private String picture;
    private String level;
    private String lesson;
}
