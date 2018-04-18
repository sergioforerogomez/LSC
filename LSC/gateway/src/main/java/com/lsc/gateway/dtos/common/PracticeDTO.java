package com.lsc.gateway.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class PracticeDTO {
    private String code;
    private List<String[]> videos;
    private List<String[]> words;
    private List<String[]> pictures;
    private List<Integer> answer;

    public PracticeDTO() {
        this.code = "";
        this.videos = new ArrayList<>();
        this.words = new ArrayList<>();
        this.pictures = new ArrayList<>();
        this.answer = new ArrayList<>();
    }
}
