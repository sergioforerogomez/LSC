package com.lsc.common.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PracticeOutputDTO extends ErrorDTO {
    private String practiceId;
    private String videos;
    private String words;
    private String pictures;
}
