package com.lsc.common.entities;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Accessors(chain = true)
public class LevelEntity {
    @Id
    private String levelId;
    private String color;
    private String name;
    private String description;
    private String image;
    private List<String> lessons;
}
