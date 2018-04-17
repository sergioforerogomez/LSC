package com.lsc.media.services;

import com.lsc.media.dtos.PictureInputDTO;
import com.lsc.media.dtos.PictureOutputDTO;
import com.lsc.media.dtos.VideoInputDTO;
import com.lsc.media.dtos.VideoOutputDTO;

public class MediaServiceImpl implements MediaService {
    @Override
    public String getVideoByWord(String word) {
        return "";
    }

    @Override
    public VideoOutputDTO postVideo(VideoInputDTO videoInputDTO) {
        return new VideoOutputDTO();
    }

    @Override
    public PictureOutputDTO postPicture(PictureInputDTO pictureInputDTO) {
        return new PictureOutputDTO();
    }

    @Override
    public void deletePicture(String word) {

    }
}
