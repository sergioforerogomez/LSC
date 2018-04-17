package com.lsc.media.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VideoInputDTO {
    private String word;
}
