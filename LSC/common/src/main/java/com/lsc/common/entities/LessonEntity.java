package com.lsc.common.entities;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
public class LessonEntity {
    @Id
    private String lessonId;
    private String color;
    private String name;
    private String image;
}
