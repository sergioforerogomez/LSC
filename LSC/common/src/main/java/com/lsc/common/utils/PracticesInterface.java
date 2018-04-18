package com.lsc.common.utils;

import com.lsc.common.dtos.PracticeDTO;
import com.lsc.common.dtos.WordDTO;

import java.util.List;

public interface PracticesInterface {
    PracticeDTO get(String lessonName, List<WordDTO> wordDTOS, String[] words, String word);
}
