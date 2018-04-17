package com.lsc.media.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PictureOutputDTO extends ErrorDTO {
    private String picture;
    private String word;
}
