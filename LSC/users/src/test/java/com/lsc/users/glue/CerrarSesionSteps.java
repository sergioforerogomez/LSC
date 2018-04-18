package com.lsc.users.glue;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import io.restassured.specification.RequestSpecification;

import static net.serenitybdd.rest.SerenityRest.given;

public class CerrarSesionSteps {
    private String logoutUrl = "http://localhost:12346/logout";
    private RequestSpecification requestSpecification;

    @Dado("^que Sergio tiene un token valido$")
    public void queSergioTieneUnTokenValido() {
        this.requestSpecification = given().contentType("application/json");
    }

    @Cuando("^realiza una peticion para cerrar sesion$")
    public void realizaUnaPeticionParaCerrarSesion() {
        this.requestSpecification.when().post(this.logoutUrl);
    }
}
