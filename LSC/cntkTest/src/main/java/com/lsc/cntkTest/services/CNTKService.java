package com.lsc.cntkTest.services;

import com.lsc.cntkTest.dtos.CNTKResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface CNTKService {
    CNTKResponseDTO postCNTK(MultipartFile file);
}
