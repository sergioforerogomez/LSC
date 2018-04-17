package com.lsc.gateway.dtos.dictionary;

import com.lsc.gateway.dtos.ErrorDTO;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WordOutputDTO extends ErrorDTO {
    private String word;
    private String concept;
    private String video;
}
