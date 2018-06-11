package com.lsc.users.glue;

import com.lsc.users.Utils.Utils;
import com.lsc.users.dtos.ProfileInputDTO;
import com.lsc.users.dtos.RegisterDTO;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import io.restassured.specification.RequestSpecification;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EditProfileSteps {
    private String createAccountUrl = "http://localhost:12346/user";
    private String editProfileUrl = "http://localhost:12346/profile";
    private RequestSpecification requestSpecification;
    private ProfileInputDTO profileInputDTO;
    private String data;
    private String email;
    private String currentPassword;

    private String getProfileId() {
        this.email = Utils.getRandomEmail();
        this.currentPassword = Utils.getRandomPassword();
        RegisterDTO registerDTO = new RegisterDTO(email, this.currentPassword, this.currentPassword, Utils.getRandomName());
        String profileId = given().contentType("application/json").body(registerDTO).post(this.createAccountUrl).then().extract().response().getBody().jsonPath().get("profileId");
        return profileId;
    }

    @Dado("^que Sergio quiere editar su \"([^\"]*)\"$")
    public void queSergioQuiereEditarSu(String attribute) {
        this.editProfileUrl += "/" + getProfileId();
        this.requestSpecification = given().contentType("application/json");
        this.profileInputDTO = new ProfileInputDTO();
        switch (attribute) {
            case "email":
                this.data = Utils.getRandomEmail();
                this.profileInputDTO.setEmail(this.data);
                break;
            case "name":
                this.data = Utils.getRandomName();
                this.profileInputDTO.setName(this.data);
                break;
            case "password":
                this.data = Utils.getRandomPassword();
                this.profileInputDTO.setPassword(this.data);
                break;
        }
    }

    @Cuando("^realiza una peticion para editar$")
    public void realizaUnaPeticionParaEditar() {
        this.requestSpecification.body(this.profileInputDTO);
        this.requestSpecification.when().put(this.editProfileUrl);
    }

    @Entonces("^el sistema retorna \"([^\"]*)\" actualizado$")
    public void elSistemaRetornaActualizado(String attribute) {
        Object response = then().extract().response().getBody().jsonPath().get(attribute);
        assertThat(response, equalTo(this.data));
    }

    @Dado("^que Sergio quiere editar su perfil con \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" y contraseña actual valida$")
    public void queSergioQuiereEditarSuPerfilConYContraseñaActualValida(String email, String password, String confirmPassword) {
        this.editProfileUrl += "/" + getProfileId();
        this.requestSpecification = given().contentType("application/json");
        this.profileInputDTO = new ProfileInputDTO().setEmail(email).setPassword(password).setConfirmPassword(confirmPassword);
        this.profileInputDTO.setCurrentPassword(this.currentPassword);
    }

    @Dado("^que Sergio quiere editar su perfil con \"([^\"]*)\", \"([^\"]*)\" y contraseña actual invalida$")
    public void queSergioQuiereEditarSuPerfilConYContraseñaActualInvalida(String password, String confirmPassword) {
        this.editProfileUrl += "/" + getProfileId();
        this.requestSpecification = given().contentType("application/json");
        this.profileInputDTO = new ProfileInputDTO().setPassword(password).setConfirmPassword(confirmPassword);
    }

    @Dado("^que Sergio quiere editar su perfil con datos existentes$")
    public void queSergioQuiereEditarSuPerfilConDatosExistentes() {
        this.editProfileUrl += "/" + getProfileId();
        this.requestSpecification = given().contentType("application/json");
        this.profileInputDTO = new ProfileInputDTO().setEmail(this.email);
    }
}
