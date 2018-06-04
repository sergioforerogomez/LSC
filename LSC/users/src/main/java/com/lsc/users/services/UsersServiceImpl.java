package com.lsc.users.services;

import com.lsc.users.dtos.*;
import com.lsc.users.entities.AchievementEntity;
import com.lsc.users.entities.ProfileEntity;
import com.lsc.users.repositories.AchievementRepository;
import com.lsc.users.repositories.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.regex.Pattern;

public class UsersServiceImpl implements UsersService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    private ModelMapper modelMapper = new ModelMapper();

    private boolean validateTwoPasswords(String password1, String password2) {
        return password1.equals(password2);
    }

    private boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        return pattern.matcher(password).matches();
    }

    private ResponseEntity<Object> validateRegisterPassword(RegisterDTO registerDTO) {
        if (validatePassword(registerDTO.getPassword())) {
            if (validateTwoPasswords(registerDTO.getPassword(), registerDTO.getConfirmPassword())) {
                ProfileEntity profileEntity = this.modelMapper.map(registerDTO, ProfileEntity.class);
                profileEntity.setLevel(Level.BASICO);
                profileEntity.setGeneralProgress(0);
                this.profileRepository.save(profileEntity);
                return postLogin(new LoginInputDTO(registerDTO.getEmail(), registerDTO.getPassword()));
            }
            return new ResponseEntity<>(new ErrorDTO("Error al crear la cuenta, las contraseñas no coinciden."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al crear la cuenta, la contraseña es invalida."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> validateRegisterEmail(RegisterDTO registerDTO) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (pattern.matcher(registerDTO.getEmail()).matches()) {
            if (this.profileRepository.findByEmail(registerDTO.getEmail()) == null) {
                return validateRegisterPassword(registerDTO);
            }
            return new ResponseEntity<>(new ErrorDTO("Error al crear la cuenta, el correo ya existe."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al crear la cuenta, el correo es invalido."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> postRegister(RegisterDTO registerDTO) {
        return validateRegisterEmail(registerDTO);
    }

    private ResponseEntity<Object> validateLoginPassword(LoginInputDTO loginInputDTO, ProfileEntity profileEntity) {
        if (loginInputDTO.getPassword().equals(profileEntity.getPassword())) {
            return new ResponseEntity<>(this.modelMapper.map(profileEntity, LoginOutputDTO.class), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al iniciar sesion, el correo y la contraseña no coinciden."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> validateLoginEmail(LoginInputDTO loginInputDTO) {
        ProfileEntity profileEntity = this.profileRepository.findByEmail(loginInputDTO.getEmail());
        if (profileEntity != null) {
            return validateLoginPassword(loginInputDTO, profileEntity);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al iniciar sesion, el correo no existe."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> postLogin(LoginInputDTO loginInputDTO) {
        return validateLoginEmail(loginInputDTO);
    }

    private boolean isProfile(String profileId) {
        return this.profileRepository.findById(profileId).isPresent();
    }

    @Override
    public ResponseEntity<Object> getProfileById(String profileId) {
        if (isProfile(profileId)) {
            ProfileEntity profileEntity = this.profileRepository.findById(profileId).get();
            return new ResponseEntity<>(this.modelMapper.map(profileEntity, ProfileOutputDTO.class).setLevel(profileEntity.getLevel().getLevel()), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al ver el perfil, el perfil no existe."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private ProfileEntity updateName(ProfileEntity profileEntity, ProfileInputDTO profileInputDTO) {
        if (profileInputDTO.getName() != null) {
            profileEntity.setName(profileInputDTO.getName());
        }
        return updatePassword(profileEntity, profileInputDTO);
    }

    private ProfileEntity updatePassword(ProfileEntity profileEntity, ProfileInputDTO profileInputDTO) {
        if (profileInputDTO.getPassword() != null && profileInputDTO.getConfirmPassword() != null && profileInputDTO.getCurrentPassword() != null) {
            if (validatePassword(profileInputDTO.getPassword())) {
                if (validateTwoPasswords(profileInputDTO.getPassword(), profileInputDTO.getConfirmPassword())) {
                    profileEntity.setPassword(profileInputDTO.getPassword());
                }
            }
        }
        return updateCompletedLessons(profileEntity, profileInputDTO);
    }

    private ProfileEntity updateCompletedLessons(ProfileEntity profileEntity, ProfileInputDTO profileInputDTO) {
        if (profileInputDTO.getCompletedLessons() != null) {
            profileEntity.addCompletedLesson(profileInputDTO.getCompletedLessons());
        }
        return profileEntity;
    }

    @Override
    public ResponseEntity<Object> putProfileById(String profileId, ProfileInputDTO profileInputDTO) {
        if (isProfile(profileId)) {
            ProfileEntity profileEntity = this.profileRepository.findById(profileId).get();
            profileEntity = updateName(profileEntity, profileInputDTO);
            this.profileRepository.save(profileEntity);
            return new ResponseEntity<>(this.modelMapper.map(profileEntity, ProfileOutputDTO.class).setLevel(profileEntity.getLevel().getLevel()), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al editar el perfil, el perfil no existe."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private boolean isAchievement(String achievementId) {
        return this.achievementRepository.findById(achievementId).isPresent();
    }

    @Override
    public ResponseEntity<Object> getAchievementById(String achievementId) {
        if (isAchievement(achievementId)) {
            AchievementEntity achievementEntity = this.achievementRepository.findById(achievementId).get();
            return new ResponseEntity<>(this.modelMapper.map(achievementEntity, AchievementDTO.class), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al ver el logro, el logro no existe."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> getAchievements() {
        return new ResponseEntity<>(this.modelMapper.map(this.achievementRepository.findAll(), AchievementDTO[].class), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> postUserAchievement(List<AchievementDTO> achievementDTOS) {
        this.achievementRepository.deleteAll();
        for (AchievementDTO achievementDTO : achievementDTOS) {
            this.achievementRepository.save(this.modelMapper.map(achievementDTO, AchievementEntity.class));
        }
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }
}
