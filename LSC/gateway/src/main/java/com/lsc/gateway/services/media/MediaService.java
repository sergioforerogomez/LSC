package com.lsc.gateway.services.media;

import com.lsc.gateway.dtos.media.PictureInputDTO;
import com.lsc.gateway.dtos.media.PictureOutputDTO;
import com.lsc.gateway.dtos.media.VideoInputDTO;
import com.lsc.gateway.dtos.media.VideoOutputDTO;

public interface MediaService {
    String getVideoByWord(String word);

    VideoOutputDTO postVideo(VideoInputDTO videoInputDTO);

    PictureOutputDTO postPicture(PictureInputDTO pictureInputDTO);

    void deletePicture(String word);
}
