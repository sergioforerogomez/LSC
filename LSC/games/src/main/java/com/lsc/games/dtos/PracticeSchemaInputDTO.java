package com.lsc.games.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PracticeSchemaInputDTO {
    private String code;
    private int videos;
    private int words;
    private int pictures;
}
