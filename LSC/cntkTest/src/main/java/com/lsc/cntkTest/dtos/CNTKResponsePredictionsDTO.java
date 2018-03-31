package com.lsc.cntkTest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CNTKResponsePredictionsDTO {
    @JsonProperty("TagId")
    private String tagId;
    @JsonProperty("Tag")
    private String tag;
    @JsonProperty("Probability")
    private double probability;
}
