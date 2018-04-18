package com.lsc.users.glue;

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
import static org.hamcrest.Matchers.*;

public class CreateAccountSteps {
    private String createAccountUrl = "http://localhost:12346/user";
    private RequestSpecification requestSpecification;
    private RegisterDTO registerDTO;

    @Dado("^que Sergio quiere crear una cuenta con datos validos$")
    public void queSergioQuiereCrearUnaCuentaConDatosValidos() {
        this.requestSpecification = given().contentType("application/json");
        this.registerDTO = new RegisterDTO("random-" + UUID.randomUUID().toString().substring(0, 8) + "@example.com", "abcdefg0", "abcdefg0");
    }

    @Dado("^que Sergio quiere crear una cuenta con datos existentes$")
    public void queSergioQuiereCrearUnaCuentaConDatosExistentes() {
        this.registerDTO = new RegisterDTO("random-" + UUID.randomUUID().toString().substring(0, 8) + "@example.com", "abcdefg0", "abcdefg0");
        given().contentType("application/json").body(this.registerDTO).post(this.createAccountUrl);
        this.requestSpecification = given().contentType("application/json");
    }

    @Dado("^que Sergio quiere crear una cuenta con \"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\"$")
    public void queSergioQuiereCrearUnaCuentaConY(String email, String password, String confirmPassword) {
        this.requestSpecification = given().contentType("application/json");
        this.registerDTO = new RegisterDTO(email, password, confirmPassword);
    }

    @Cuando("^realiza una peticion para crear la cuenta$")
    public void realizaUnaPeticionParaCrearLaCuenta() {
        this.requestSpecification.body(this.registerDTO);
        this.requestSpecification.when().post(this.createAccountUrl);
    }

    @Entonces("^el sistema retorna un token$")
    public void elSistemaUnRetornaToken() {
        String responseToken = then().extract().response().getBody().jsonPath().get("token");
        assertThat(responseToken, is(nullValue()));
    }

    @Y("^el id del perfil$")
    public void elIdDelPerfil() {
        String responseProfileId = then().extract().response().getBody().jsonPath().get("profileId");
        assertThat(responseProfileId, is(notNullValue()));
    }
}
