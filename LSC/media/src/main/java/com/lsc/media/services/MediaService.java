package com.lsc.media.services;

import com.lsc.media.dtos.PictureInputDTO;
import com.lsc.media.dtos.PictureOutputDTO;
import com.lsc.media.dtos.VideoInputDTO;
import com.lsc.media.dtos.VideoOutputDTO;

public interface MediaService {
    String getVideoByWord(String word);

    VideoOutputDTO postVideo(VideoInputDTO videoInputDTO);

    PictureOutputDTO postPicture(PictureInputDTO pictureInputDTO);

    void deletePicture(String word);
}
