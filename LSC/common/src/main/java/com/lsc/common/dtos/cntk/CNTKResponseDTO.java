package com.lsc.common.dtos.cntk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CNTKResponseDTO {
    private String id;
    private String project;
    private String iteration;
    private String created;
    private List<CNTKResponsePredictionsDTO> predictions;
}
