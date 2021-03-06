package com.lsc.common.dtos.cntk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CNTKResponsePredictionsDTO {
    private String tagId;
    private String tagName;
    private double probability;
}
