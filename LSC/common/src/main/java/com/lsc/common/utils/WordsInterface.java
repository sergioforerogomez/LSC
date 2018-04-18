package com.lsc.common.utils;

import com.lsc.common.dtos.WordDTO;

import java.util.List;

public interface WordsInterface {
    WordDTO get(List<WordDTO> wordDTOS);
}
