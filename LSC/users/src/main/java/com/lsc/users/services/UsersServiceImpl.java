package com.lsc.users.services;

import com.lsc.users.dtos.*;
import com.lsc.users.entities.AchievementEntity;
import com.lsc.users.entities.ProfileEntity;
import com.lsc.users.repositories.AchievementRepository;
import com.lsc.users.repositories.ProfileRepository;
import com.lsc.users.utils.AchievementsService;
import org.bson.types.ObjectId;
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
    @Autowired
    private TokenService tokenService;

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
                profileEntity.setProgressName(ProgressName.BASICO);
                profileEntity.setGeneralProgress(0);
                this.profileRepository.save(profileEntity);
                return postLogin(new LoginInputDTO(registerDTO.getEmail(), registerDTO.getPassword()));
            }
            return new ResponseEntity<>(new ErrorDTO("Error al crear la cuenta, las contraseñas no coinciden."), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al crear la cuenta, la contraseña debe contener al menos una letra mayúscula, un número y 8 caracteres."), new HttpHeaders(), HttpStatus.OK);
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        return pattern.matcher(email).matches();
    }

    private ResponseEntity<Object> validateRegisterEmail(RegisterDTO registerDTO) {
        if (validateEmail(registerDTO.getEmail())) {
            if (this.profileRepository.findByEmail(registerDTO.getEmail()) == null) {
                return validateRegisterPassword(registerDTO);
            }
            return new ResponseEntity<>(new ErrorDTO("Error al crear la cuenta, el correo ya existe."), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al crear la cuenta, el correo es invalido."), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> postRegister(RegisterDTO registerDTO) {
        return validateRegisterEmail(registerDTO);
    }

    private ResponseEntity<Object> validateLoginPassword(LoginInputDTO loginInputDTO, ProfileEntity profileEntity) {
        if (loginInputDTO.getPassword().equals(profileEntity.getPassword())) {
            LoginOutputDTO loginOutputDTO = this.modelMapper.map(profileEntity, LoginOutputDTO.class);
            loginOutputDTO.setToken(this.tokenService.createToken(profileEntity.getProfileId()));
            return new ResponseEntity<>(loginOutputDTO, new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al iniciar sesion, el correo y la contraseña no coinciden."), new HttpHeaders(), HttpStatus.OK);
    }

    private ResponseEntity<Object> validateLoginEmail(LoginInputDTO loginInputDTO) {
        ProfileEntity profileEntity = this.profileRepository.findByEmail(loginInputDTO.getEmail());
        if (profileEntity != null) {
            return validateLoginPassword(loginInputDTO, profileEntity);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al iniciar sesion, el correo no existe."), new HttpHeaders(), HttpStatus.OK);
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
            return new ResponseEntity<>(this.modelMapper.map(profileEntity, ProfileOutputDTO.class).setProgressName(profileEntity.getProgressName().getLevel()), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al ver el perfil, el perfil no existe."), new HttpHeaders(), HttpStatus.OK);
    }

    private ResponseEntity<Object> canUpdateEmail(ProfileInputDTO profileInputDTO) {
        if (profileInputDTO.getEmail() != null) {
            if (!validateEmail(profileInputDTO.getEmail())) {
                return new ResponseEntity<>(new ErrorDTO("Error al editar el perfil, el correo es invalido."), new HttpHeaders(), HttpStatus.OK);
            }
            if (this.profileRepository.findByEmail(profileInputDTO.getEmail()) != null) {
                return new ResponseEntity<>(new ErrorDTO("Error al editar el perfil, el correo ya existe."), new HttpHeaders(), HttpStatus.OK);
            }
        }
        return null;
    }

    private ResponseEntity<Object> canUpdatePassword(ProfileEntity profileEntity, ProfileInputDTO profileInputDTO) {
        if (profileInputDTO.getPassword() != null) {
            if (!validateTwoPasswords(profileEntity.getPassword(), profileInputDTO.getCurrentPassword())) {
                return new ResponseEntity<>(new ErrorDTO("Error al editar el perfil, la contraseña actual no coincide."), new HttpHeaders(), HttpStatus.OK);
            }
            if (!validatePassword(profileInputDTO.getPassword())) {
                return new ResponseEntity<>(new ErrorDTO("Error al editar el perfil, la contraseña debe contener al menos una letra mayúscula, un número y 8 caracteres."), new HttpHeaders(), HttpStatus.OK);
            }
            if (!validateTwoPasswords(profileInputDTO.getPassword(), profileInputDTO.getConfirmPassword())) {
                return new ResponseEntity<>(new ErrorDTO("Error al editar el perfil, las contraseñas no coinciden."), new HttpHeaders(), HttpStatus.OK);
            }
        }
        return null;
    }

    private void updateEmail(ProfileEntity profileEntity, ProfileInputDTO profileInputDTO) {
        if (profileInputDTO.getEmail() != null) {
            profileEntity.setEmail(profileInputDTO.getEmail());
        }
        updatePassword(profileEntity, profileInputDTO);
    }

    private void updatePassword(ProfileEntity profileEntity, ProfileInputDTO profileInputDTO) {
        if (profileInputDTO.getPassword() != null && profileInputDTO.getConfirmPassword() != null && profileInputDTO.getCurrentPassword() != null) {
            profileEntity.setPassword(profileInputDTO.getPassword());
        }
        updateName(profileEntity, profileInputDTO);
    }

    private void updateName(ProfileEntity profileEntity, ProfileInputDTO profileInputDTO) {
        if (profileInputDTO.getName() != null) {
            profileEntity.setName(profileInputDTO.getName());
        }
        updateCompletedLessons(profileEntity, profileInputDTO);
    }

    private void updateCompletedLessons(ProfileEntity profileEntity, ProfileInputDTO profileInputDTO) {
        if (profileInputDTO.getCompletedLesson() != null) {
            AchievementsService.updateAchievementsByCompletedLesson(profileEntity, profileInputDTO.getCompletedLesson());
            profileEntity.addCompletedLesson(profileInputDTO.getCompletedLesson());
        }
    }

    @Override
    public ResponseEntity<Object> putProfileById(String profileId, ProfileInputDTO profileInputDTO) {
        ResponseEntity<Object> responseEntity;
        if (isProfile(profileId)) {
            ProfileEntity profileEntity = this.profileRepository.findById(profileId).get();
            responseEntity = canUpdateEmail(profileInputDTO);
            if (responseEntity != null) {
                return responseEntity;
            }
            responseEntity = canUpdatePassword(profileEntity, profileInputDTO);
            if (responseEntity != null) {
                return responseEntity;
            }
            updateEmail(profileEntity, profileInputDTO);
            this.profileRepository.save(profileEntity);
            return new ResponseEntity<>(this.modelMapper.map(profileEntity, ProfileOutputDTO.class).setProgressName(profileEntity.getProgressName().getLevel()), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorDTO("Error al editar el perfil, el perfil no existe."), new HttpHeaders(), HttpStatus.OK);
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
        return new ResponseEntity<>(new ErrorDTO("Error al ver el logro, el logro no existe."), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAchievements() {
        return new ResponseEntity<>(this.modelMapper.map(this.achievementRepository.findAll(), AchievementDTO[].class), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> postUserProfile(List<ProfileInputDTO> profileInputDTOS) {
        this.profileRepository.deleteAll();
        for (ProfileInputDTO profileInputDTO : profileInputDTOS) {
            this.profileRepository.save(this.modelMapper.map(profileInputDTO, ProfileEntity.class));
        }
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> postUserAchievement(List<AchievementDTO> achievementDTOS) {
        this.achievementRepository.deleteAll();
        for (AchievementDTO achievementDTO : achievementDTOS) {
            this.achievementRepository.save(this.modelMapper.map(achievementDTO, AchievementEntity.class));
        }
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getToken() {
        return new ResponseEntity<>(new TokenDTO(tokenService.createToken(new ObjectId())), new HttpHeaders(), HttpStatus.OK);
    }
}
