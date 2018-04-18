package com.lsc.dictionary.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class WordDTO {
    private String word;
    private String video;
    private String picture;
    private String level;
    private String lesson;
}
