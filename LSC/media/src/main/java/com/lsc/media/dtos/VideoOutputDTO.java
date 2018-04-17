package com.lsc.media.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VideoOutputDTO extends ErrorDTO {
    private String video;
    private String word;
}
