package com.lsc.users.glue;

import com.lsc.users.Utils.Utils;
import com.lsc.users.dtos.RegisterDTO;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import io.restassured.specification.RequestSpecification;

import static net.serenitybdd.rest.SerenityRest.given;

public class CreateAccountSteps {
    private String createAccountUrl = "http://localhost:12346/user";
    private RequestSpecification requestSpecification;
    private RegisterDTO registerDTO;

    @Dado("^que Sergio quiere crear una cuenta con datos validos$")
    public void queSergioQuiereCrearUnaCuentaConDatosValidos() {
        this.requestSpecification = given().contentType("application/json");
        String password = Utils.getRandomPassword();
        this.registerDTO = new RegisterDTO(Utils.getRandomEmail(), password, password, Utils.getRandomName());
    }

    @Cuando("^realiza una peticion para crear la cuenta$")
    public void realizaUnaPeticionParaCrearLaCuenta() {
        this.requestSpecification.body(this.registerDTO);
        this.requestSpecification.when().post(this.createAccountUrl);
    }

    @Dado("^que Sergio quiere crear una cuenta con \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\"$")
    public void queSergioQuiereCrearUnaCuentaConY(String email, String password, String confirmPassword, String name) {
        this.requestSpecification = given().contentType("application/json");
        this.registerDTO = new RegisterDTO(email, password, confirmPassword, name);
    }

    @Dado("^que Sergio quiere crear una cuenta con datos existentes$")
    public void queSergioQuiereCrearUnaCuentaConDatosExistentes() {
        String password = Utils.getRandomPassword();
        this.registerDTO = new RegisterDTO(Utils.getRandomEmail(), password, password, Utils.getRandomName());
        given().contentType("application/json").body(this.registerDTO).post(this.createAccountUrl);
        this.requestSpecification = given().contentType("application/json");
    }
}
