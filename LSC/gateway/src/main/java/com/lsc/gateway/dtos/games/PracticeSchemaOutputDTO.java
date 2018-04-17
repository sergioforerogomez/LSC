package com.lsc.gateway.dtos.games;

import com.lsc.gateway.dtos.ErrorDTO;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PracticeSchemaOutputDTO extends ErrorDTO {
    private String code;
    private int videos;
    private int words;
    private int pictures;
}
