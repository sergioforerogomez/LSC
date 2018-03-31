package com.lsc.cntkTest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CNTKResponseDTO {
    @JsonProperty("Id")
    private String id;
    @JsonProperty("Project")
    private String project;
    @JsonProperty("Iteration")
    private String iteration;
    @JsonProperty("Created")
    private String created;
    @JsonProperty("Predictions")
    private List<CNTKResponsePredictionsDTO> predictions;
}
