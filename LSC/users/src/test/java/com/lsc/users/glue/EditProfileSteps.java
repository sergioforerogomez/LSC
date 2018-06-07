package com.lsc.users.glue;

import com.lsc.users.dtos.ProfileInputDTO;
import com.lsc.users.dtos.RegisterDTO;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import io.restassured.specification.RequestSpecification;

import java.util.UUID;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class EditProfileSteps {
    private String createAccountUrl = "http://localhost:12346/user";
    private String editProfileUrl = "http://localhost:12346/profile";
    private RequestSpecification requestSpecification;
    private ProfileInputDTO profileInputDTO;

    private String getProfileId() {
        RegisterDTO registerDTO = new RegisterDTO(UUID.randomUUID().toString().substring(0, 8) + "@example.com", "abcdefg0", "abcdefg0");
        String profileId = given().contentType("application/json").body(registerDTO).post(this.createAccountUrl).then().extract().response().getBody().jsonPath().get("profileId");
        return profileId;
    }

    @Dado("^que Sergio quiere editar su \"([^\"]*)\"$")
    public void queSergioQuiereEditarSuDelPerfil(String attribute) {
        this.editProfileUrl += "/" + getProfileId();
        this.requestSpecification = given().contentType("application/json");
        this.profileInputDTO = new ProfileInputDTO();
        switch (attribute) {
            case "name":
                this.profileInputDTO.setName(UUID.randomUUID().toString().substring(0, 8));
                break;
            case "password":
                this.profileInputDTO.setPassword(UUID.randomUUID().toString().substring(0, 8));
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
        String response = then().extract().response().getBody().jsonPath().get(attribute);
        assertThat(response, is(notNullValue()));
    }
}
