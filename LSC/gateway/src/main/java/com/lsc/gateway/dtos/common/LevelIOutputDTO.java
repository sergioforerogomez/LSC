package com.lsc.gateway.dtos.common;

import com.lsc.gateway.dtos.ErrorDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class LevelIOutputDTO extends ErrorDTO {
    private String levelId;
    private String title;
    private String image;
    private List<String> practices;
}
