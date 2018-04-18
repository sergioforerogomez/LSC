package com.lsc.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class LevelDTO {
    private String levelId;
    private String color;
    private String name;
    private String description;
    private String image;
    private List<String> lessons;
}
