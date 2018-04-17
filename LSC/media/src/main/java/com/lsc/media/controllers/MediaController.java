package com.lsc.media.controllers;

import com.lsc.media.dtos.PictureInputDTO;
import com.lsc.media.dtos.PictureOutputDTO;
import com.lsc.media.dtos.VideoInputDTO;
import com.lsc.media.dtos.VideoOutputDTO;
import com.lsc.media.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class MediaController {
    private MediaService mediaService;

    @Autowired
    public MediaController(@Qualifier("media") MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/video/{word}")
    public String getVideoByWord(@PathVariable String word) {
        return this.mediaService.getVideoByWord(word);
    }

    @PostMapping("/video")
    public VideoOutputDTO postVideo(@RequestBody VideoInputDTO videoInputDTO) {
        return this.mediaService.postVideo(videoInputDTO);
    }

    @PostMapping("/picture")
    public PictureOutputDTO postPicture(@RequestBody PictureInputDTO pictureInputDTO) {
        return this.mediaService.postPicture(pictureInputDTO);
    }

    @DeleteMapping("/picture/{word}")
    public void deletePicture(@PathVariable String word) {
        this.mediaService.deletePicture(word);
    }
}
