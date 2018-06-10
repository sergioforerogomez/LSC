package com.lsc.users.glue;

import com.lsc.users.dtos.LoginInputDTO;
import com.lsc.users.dtos.RegisterDTO;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import io.restassured.specification.RequestSpecification;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class LogInSteps {
    private String createAccountUrl = "http://localhost:12346/user";
    private String loginUrl = "http://localhost:12346/login";
    private RequestSpecification requestSpecification;
    private LoginInputDTO loginInputDTO;
    private RestTemplate restTemplate = new RestTemplate();

    @Dado("^que Sergio quiere iniciar sesion con datos existentes")
    public void queSergioQuiereIniciarSesionConDatosValidos() {
        String email = "random-" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
        RegisterDTO registerDTO = new RegisterDTO(email, "abcdefg0", "abcdefg0", "Sergio");
        given().contentType("application/json").body(registerDTO).post(this.createAccountUrl);
        this.requestSpecification = given().contentType("application/json");
        this.loginInputDTO = new LoginInputDTO(email, "abcdefg0");
    }

    @Dado("^que Sergio quiere iniciar sesion con un correo inexistente$")
    public void queSergioQuiereIniciarSesionConUnCorreoInexistente() {
        this.requestSpecification = given().contentType("application/json");
        this.loginInputDTO = new LoginInputDTO("random-" + UUID.randomUUID().toString().substring(0, 8) + "@example.com", "abcdefg0");
    }

    @Y("^contraseña incorrecta$")
    public void contraseñaIncorrecta() {
        this.loginInputDTO.setPassword("");
    }

    @Cuando("^realiza una peticion para iniciar sesion$")
    public void realizaUnaPeticionParaIniciarSesion() {
        this.requestSpecification.body(this.loginInputDTO);
        this.requestSpecification.when().post(this.loginUrl);
    }

    @Entonces("^el sistema almacena y retorna un token$")
    public void elSistemaAlmacenaYRetornaUnToken() {
        String responseToken = then().extract().response().getBody().jsonPath().get("token");
        assertThat(responseToken, is(nullValue()));
    }
}
