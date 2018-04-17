package com.lsc.media.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PictureInputDTO {
    private String word;
}
