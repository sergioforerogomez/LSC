package com.lsc.common.services;

import com.lsc.common.dtos.*;
import com.lsc.common.dtos.cntk.CNTKOutputDTO;
import com.lsc.common.dtos.cntk.CNTKResponseDTO;
import com.lsc.common.entities.LessonEntity;
import com.lsc.common.entities.LevelEntity;
import com.lsc.common.repositories.LessonRepository;
import com.lsc.common.repositories.LevelRepository;
import com.lsc.common.utils.PracticesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CommonServiceImpl implements CommonService {
    @Value("${endpoint.url.abecedary-1}")
    private String abecedary1Url;
    @Value("${endpoint.url.abecedary-2}")
    private String abecedary2Url;
    @Value("${endpoint.url.numbers}")
    private String numbersUrl;
    @Value("${abecedary.predictionKey}")
    private String abecedaryPredictionsKey;
    @Value("${numbers.predictionKey}")
    private String numbersPredictionKey;

    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private LevelRepository levelRepository;

    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<Object> getLevels() {
        return new ResponseEntity<>(this.modelMapper.map(this.levelRepository.findAll(), LevelDTO[].class), new HttpHeaders(), HttpStatus.OK);
    }

    private boolean isLevel(String levelId) {
        return this.levelRepository.findById(levelId).isPresent();
    }

    @Override
    public ResponseEntity<Object> getLessonsByLevelId(String levelId) {
        if (isLevel(levelId)) {
            List<LessonEntity> lessonEntities = new ArrayList<>();
            LevelEntity levelEntity = this.levelRepository.findById(levelId).get();
            for (String lessonId : levelEntity.getLessons()) {
                lessonEntities.add(this.lessonRepository.findById(lessonId).get());
            }
            return new ResponseEntity<>(this.modelMapper.map(lessonEntities, LessonDTO[].class), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al ver las lecciones, el nivel no existe."), new HttpHeaders(), HttpStatus.OK);
    }

    private boolean isLesson(String lessonId) {
        return this.lessonRepository.findById(lessonId).isPresent();
    }

    @Override
    public ResponseEntity<Object> postPracticeByLessonId(String lessonId, List<WordDTO> wordDTOS) {
        if (isLesson(lessonId)) {
            LessonEntity lessonEntity = this.lessonRepository.findById(lessonId).get();
            List<PracticeDTO> practiceDTOS = PracticesService.getPracticesByLessonName(lessonEntity.getName(), wordDTOS);
            return new ResponseEntity<>(this.modelMapper.map(practiceDTOS, PracticeDTO[].class), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al ver la practica, la lección no existe."), new HttpHeaders(), HttpStatus.OK);
    }

    private ResponseEntity<Object> checkImage(CNTKResponseDTO cntkResponseDTO, String tag) {
        if (cntkResponseDTO.getPredictions().stream().anyMatch(item -> item.getTagName().equals(tag))) {
            double probability = cntkResponseDTO.getPredictions().stream().filter(item -> item.getTagName().equals(tag)).findFirst().get().getProbability() * 100;
            return new ResponseEntity<>(this.modelMapper.map(new CNTKOutputDTO(cntkResponseDTO.getPredictions().get(0).getTagName().equals(tag), probability), CNTKOutputDTO.class), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al analizar la palabra, la palabra no existe."), new HttpHeaders(), HttpStatus.OK);
    }

    private boolean getCNTKAbecedaryUrl(String tag) {
        return tag.equals("A") ||
                tag.equals("B") ||
                tag.equals("C") ||
                tag.equals("F") ||
                tag.equals("K") ||
                tag.equals("M") ||
                tag.equals("Q") ||
                tag.equals("R") ||
                tag.equals("T") ||
                tag.equals("W") ||
                tag.equals("Y");
    }

    private String getCNTKUrl(String tag) {
        Pattern pattern = Pattern.compile("[0-9]");
        return pattern.matcher(tag).matches() ? this.numbersUrl : getCNTKAbecedaryUrl(tag) ? this.abecedary1Url : this.abecedary2Url;
    }

    private String getCNTKPredictionKey(String tag) {
        Pattern pattern = Pattern.compile("[0-9]");
        return pattern.matcher(tag).matches() ? this.numbersPredictionKey : this.abecedaryPredictionsKey;
    }

    @Override
    public ResponseEntity<Object> postCNTK(String tag, MultipartFile file) {
        CNTKResponseDTO cntkResponseDTO = null;
        String url = getCNTKUrl(tag);
        String predictionKey = getCNTKPredictionKey(tag);
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Prediction-Key", predictionKey);
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<ByteArrayResource> httpEntity = new HttpEntity<>(new ByteArrayResource(file.getBytes()), httpHeaders);
            cntkResponseDTO = this.restTemplate.postForObject(url, httpEntity, CNTKResponseDTO.class);
            return checkImage(cntkResponseDTO, tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ErrorDTO("Error al reconocer la palabra."), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> postCommonLevel(List<LevelDTO> levelDTOS) {
        this.levelRepository.deleteAll();
        for (LevelDTO levelDTO : levelDTOS) {
            this.levelRepository.save(this.modelMapper.map(levelDTO, LevelEntity.class));
        }
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> postCommonLesson(List<LessonDTO> lessonDTOS) {
        this.lessonRepository.deleteAll();
        for (LessonDTO lessonDTO : lessonDTOS) {
            this.lessonRepository.save(this.modelMapper.map(lessonDTO, LessonEntity.class));
        }
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }
}
