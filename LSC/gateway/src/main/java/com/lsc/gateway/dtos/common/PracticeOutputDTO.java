package com.lsc.gateway.dtos.common;

import com.lsc.gateway.dtos.ErrorDTO;
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
