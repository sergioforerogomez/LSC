package com.lsc.users.glue;

import com.lsc.users.Utils.Utils;
import com.lsc.users.dtos.RegisterDTO;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import io.restassured.specification.RequestSpecification;

import java.util.UUID;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ViewProfileSteps {
    private String createAccountUrl = "http://localhost:12346/user";
    private String viewProfileUrl = "http://localhost:12346/profile";
    private String viewAchievementUrl = "http://localhost:12346/achievement";
    private RequestSpecification requestSpecification;

    private String getProfileId() {
        String password = Utils.getRandomPassword();
        RegisterDTO registerDTO = new RegisterDTO(Utils.getRandomEmail(), password, password, Utils.getRandomName());
        String profileId = given().contentType("application/json").body(registerDTO).post(this.createAccountUrl).then().extract().response().getBody().jsonPath().get("profileId");
        return profileId;
    }

    @Dado("^que Sergio quiere ver los datos de su perfil$")
    public void queSergioQuiereVerLosDatosDeSuPerfil() {
        this.viewProfileUrl += "/" + getProfileId();
        this.requestSpecification = given().contentType("application/json");
    }

    @Cuando("^realiza una peticion para ver el perfil$")
    public void realizaUnaPeticionParaVerElPerfil() {
        this.requestSpecification.when().get(this.viewProfileUrl);
    }

    @Y("^\"([^\"]*)\" del perfil$")
    public void delPerfil(String attribute) {
        Object response = then().extract().response().getBody().jsonPath().get(attribute);
        assertThat(response, is(notNullValue()));
    }

    @Dado("^que Sergio quiere ver los datos de su perfil con un id invalido$")
    public void queSergioQuiereVerLosDatosDeSuPerfilConUnIdInvalido() {
        this.viewProfileUrl += "/" + UUID.randomUUID().toString().substring(0, 8);
        this.requestSpecification = given().contentType("application/json");
    }

    @Dado("^que Sergio quiere ver los datos de todos los logros$")
    public void queSergioQuiereVerLosDatosDeTodosLosLogros() {
        this.requestSpecification = given().contentType("application/json");
    }

    @Cuando("^realiza una peticion para ver logros")
    public void realizaUnaPeticionParaVerLogros() {
        this.requestSpecification.when().get(this.viewAchievementUrl);
    }

    @Entonces("^el sistema retorna los logros$")
    public void elSistemaRetornaLosLogros() {
        Object response = then().extract().response().getBody().jsonPath().getList("");
        assertThat(response, is(notNullValue()));
    }

    @Dado("^que Sergio quiere ver los datos de un logro con un id invalido$")
    public void queSergioQuiereVerLosDatosDeUnLogroConUnIdInvalido() {
        this.viewAchievementUrl += "/" + UUID.randomUUID().toString().substring(0, 8);
        this.requestSpecification = given().contentType("application/json");
    }
}
