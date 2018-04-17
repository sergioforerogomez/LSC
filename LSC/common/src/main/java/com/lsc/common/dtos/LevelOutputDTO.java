package com.lsc.common.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class LevelOutputDTO extends ErrorDTO {
    private String levelId;
    private String title;
    private String image;
    private List<String> practices;
}
