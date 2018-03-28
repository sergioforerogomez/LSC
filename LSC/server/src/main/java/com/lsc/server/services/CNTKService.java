package com.lsc.server.services;

import com.lsc.server.dtos.CNTKResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface CNTKService {
    CNTKResponseDTO postCNTK(MultipartFile multipartFile);
}
