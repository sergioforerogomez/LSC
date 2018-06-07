package com.lsc.dictionary.services;

import com.google.gson.JsonObject;
import com.lsc.dictionary.dtos.ErrorDTO;
import com.lsc.dictionary.dtos.WordDTO;
import com.lsc.dictionary.entities.WordEntity;
import com.lsc.dictionary.repositories.WordRepository;
import com.lsc.dictionary.utils.AmazonClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private WordRepository wordRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private AmazonClient amazonClient;
    @Value("${amazonProperties.videoBucketName}")
    private String videoBuckeName;
    @Value("${amazonProperties.pictureBucketName}")
    private String pictureBuckeName;

    @Override
    public ResponseEntity<Object> getWords() {
        List<WordEntity> words = this.wordRepository.findAll();
        Collections.sort(words, new Comparator<WordEntity>() {
            public int compare(WordEntity wordEntity1, WordEntity wordEntity2) {
                return wordEntity1.getWord().compareTo(wordEntity2.getWord());
            }
        });
        return new ResponseEntity<>(this.modelMapper.map(words, WordDTO[].class), new HttpHeaders(), HttpStatus.OK);
    }

    private WordEntity findWordByWord(String word) {
        return this.wordRepository.findByWord(word);
    }

    @Override
    public ResponseEntity<Object> getWordByWord(String word) {
        WordEntity wordEntity = findWordByWord(word);
        if (wordEntity != null) {
            return new ResponseEntity<>(this.modelMapper.map(wordEntity, WordDTO.class), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al ver la palabra, la palabra no existe."), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> postVideo(MultipartFile videoFile) {
        JsonObject url = new JsonObject();
        url.addProperty("url", this.amazonClient.uploadFile(videoFile, this.videoBuckeName));
        return new ResponseEntity<>(url.toString(), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> postPicture(MultipartFile pictureFile) {
        JsonObject url = new JsonObject();
        url.addProperty("url", this.amazonClient.uploadFile(pictureFile, this.pictureBuckeName));
        return new ResponseEntity<>(url.toString(), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> postWord(WordDTO wordDTO) {
        WordEntity wordEntity = findWordByWord(wordDTO.getWord());
        if (wordEntity == null) {
            wordEntity = this.modelMapper.map(wordDTO, WordEntity.class);
            this.wordRepository.save(wordEntity);
            return new ResponseEntity<>(this.modelMapper.map(wordEntity, WordDTO.class), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al crear la palabra, la palabra ya existe."), new HttpHeaders(), HttpStatus.OK);
    }

    private WordEntity updatePicture(WordEntity wordEntity, WordDTO wordDTO) {
        if (wordDTO.getPicture() != null) {
            wordEntity.setPicture(wordDTO.getPicture());
        }
        return wordEntity;
    }

    private WordEntity updateVideo(WordEntity wordEntity, WordDTO wordDTO) {
        if (wordDTO.getVideo() != null) {
            wordEntity.setVideo(wordDTO.getVideo());
        }
        return updatePicture(wordEntity, wordDTO);
    }

    private WordEntity updateLesson(WordEntity wordEntity, WordDTO wordDTO) {
        if (wordDTO.getLesson() != null) {
            wordEntity.setLesson(wordDTO.getLesson());
        }
        return updateVideo(wordEntity, wordDTO);
    }

    private WordEntity updateLevel(WordEntity wordEntity, WordDTO wordDTO) {
        if (wordDTO.getLevel() != null) {
            wordEntity.setLevel(wordDTO.getLevel());
        }
        return updateLesson(wordEntity, wordDTO);
    }

    private WordEntity updateWord(WordEntity wordEntity, WordDTO wordDTO) {
        if (wordDTO.getWord() != null) {
            wordEntity.setWord(wordDTO.getWord());
        }
        return updateLevel(wordEntity, wordDTO);
    }

    @Override
    public ResponseEntity<Object> putWordByWord(String word, WordDTO wordDTO) {
        WordEntity wordEntity = findWordByWord(wordDTO.getWord());
        if (wordEntity != null) {
            wordEntity = updateWord(wordEntity, wordDTO);
            this.wordRepository.save(wordEntity);
            return new ResponseEntity<>(this.modelMapper.map(wordEntity, WordDTO.class), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al actualizar la palabra, la palabra no existe."), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteWordByWord(String word) {
        WordEntity wordEntity = findWordByWord(word);
        if (wordEntity != null) {
            this.amazonClient.deleteFile(this.videoBuckeName, wordEntity.getVideo().substring(wordEntity.getVideo().lastIndexOf("/") + 1));
            this.amazonClient.deleteFile(this.pictureBuckeName, wordEntity.getPicture().substring(wordEntity.getPicture().lastIndexOf("/") + 1));
            this.wordRepository.delete(wordEntity);
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al eliminar la palabra, la palabra no existe."), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteVideoByName(String name) {
        this.amazonClient.deleteFile(this.videoBuckeName, name);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deletePictureByName(String name) {
        this.amazonClient.deleteFile(this.pictureBuckeName, name);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> postDictionary(List<WordDTO> wordDTOS) {
        this.wordRepository.deleteAll();
        for (WordDTO wordDTO : wordDTOS) {
            this.wordRepository.save(this.modelMapper.map(wordDTO, WordEntity.class));
        }
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }
}
