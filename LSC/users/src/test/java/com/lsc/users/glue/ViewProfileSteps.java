package com.lsc.users.glue;

import com.lsc.users.dtos.RegisterDTO;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.UUID;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ViewProfileSteps {
    private String createAccountUrl = "http://localhost:12346/user";
    private String viewProfileUrl = "http://localhost:12346/profile";
    private String viewAchievementUrl = "http://localhost:12346/achievement";
    private RequestSpecification requestSpecification;

    private String getProfileId() {
        RegisterDTO registerDTO = new RegisterDTO(UUID.randomUUID().toString().substring(0, 8) + "@example.com", "abcdefg0", "abcdefg0", "Sergio");
        String profileId = given().contentType("application/json").body(registerDTO).post(this.createAccountUrl).then().extract().response().getBody().jsonPath().get("profileId");
        return profileId;
    }

    @Dado("^que Sergio quiere ver los datos de su perfil$")
    public void queSergioQuiereVerLosDatosDeSuPerfil() {
        this.viewProfileUrl += "/" + getProfileId();
        this.requestSpecification = given().contentType("application/json");
    }

    @Dado("^que Sergio quiere ver los datos de su perfil con un id invalido$")
    public void queSergioQuiereVerLosDatosDeSuPerfilConUnIdInvalido() {
        this.viewProfileUrl += "/" + UUID.randomUUID().toString().substring(0, 8);
        this.requestSpecification = given().contentType("application/json");
    }

    @Dado("^que Sergio quiere ver los datos de un logro con un id invalido$")
    public void queSergioQuiereVerLosDatosDeUnLogroConUnIdInvalido() {
        this.viewAchievementUrl += "/" + UUID.randomUUID().toString().substring(0, 8);
        this.requestSpecification = given().contentType("application/json");
    }

    @Dado("^que Sergio quiere ver los datos de todos los logros$")
    public void queSergioQuiereVerLosDatosDeTodosLosLogros() {
        this.requestSpecification = given().contentType("application/json");
    }

    @Cuando("^realiza una peticion para ver el perfil$")
    public void realizaUnaPeticionParaVerElPerfil() {
        this.requestSpecification.when().get(this.viewProfileUrl);
    }


    @Cuando("^realiza una peticion para ver el logro$")
    public void realizaUnaPeticionParaVerElLogro() {
        this.requestSpecification.when().get(this.viewAchievementUrl);
    }

    @Cuando("^realiza una peticion para ver los logros$")
    public void realizaUnaPeticionParaVerLosLogros() {
        this.requestSpecification.when().get(this.viewAchievementUrl);
    }

    @Entonces("^el sistema retorna los logros$")
    public void elSistemaRetornaLosLogros() {
        List<String> response = then().extract().response().getBody().jsonPath().getList("");
        assertThat(response, is(notNullValue()));
    }

    @Y("^\"([^\"]*)\" del perfil$")
    public void delPerfil(String attribute) {
        switch (attribute) {
            case "profileId":
                String profileId = then().extract().response().getBody().jsonPath().get(attribute);
                assertThat(profileId, is(notNullValue()));
                break;
            case "generalProgress":
                int generalProgress = then().extract().response().getBody().jsonPath().get(attribute);
                assertThat(generalProgress, equalTo(0));
                break;
            default:
                String response = then().extract().response().getBody().jsonPath().get(attribute);
                assertThat(response, is(nullValue()));
                break;
        }
    }
}
