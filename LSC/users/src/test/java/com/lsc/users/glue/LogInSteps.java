package com.lsc.users.glue;

import com.lsc.users.Utils.Utils;
import com.lsc.users.dtos.LoginInputDTO;
import com.lsc.users.dtos.RegisterDTO;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Y;
import io.restassured.specification.RequestSpecification;

import static net.serenitybdd.rest.SerenityRest.given;

public class LogInSteps {
    private String createAccountUrl = "http://localhost:12346/user";
    private String loginUrl = "http://localhost:12346/login";
    private RequestSpecification requestSpecification;
    private LoginInputDTO loginInputDTO;

    @Dado("^que Sergio quiere iniciar sesion con datos existentes")
    public void queSergioQuiereIniciarSesionConDatosValidos() {
        String email = Utils.getRandomEmail();
        String password = Utils.getRandomPassword();
        RegisterDTO registerDTO = new RegisterDTO(email, password, password, Utils.getRandomName());
        given().contentType("application/json").body(registerDTO).post(this.createAccountUrl);
        this.requestSpecification = given().contentType("application/json");
        this.loginInputDTO = new LoginInputDTO(email, password);
    }

    @Cuando("^realiza una peticion para iniciar sesion$")
    public void realizaUnaPeticionParaIniciarSesion() {
        this.requestSpecification.body(this.loginInputDTO);
        this.requestSpecification.when().post(this.loginUrl);
    }

    @Dado("^que Sergio quiere iniciar sesion con un correo inexistente$")
    public void queSergioQuiereIniciarSesionConUnCorreoInexistente() {
        this.requestSpecification = given().contentType("application/json");
        this.loginInputDTO = new LoginInputDTO(Utils.getRandomEmail(), Utils.getRandomPassword());
    }

    @Y("^contraseña incorrecta$")
    public void contraseñaIncorrecta() {
        this.loginInputDTO.setPassword("");
    }
}
