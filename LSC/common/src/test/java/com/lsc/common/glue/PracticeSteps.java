package com.lsc.common.glue;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.UUID;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PracticeSteps {
    private String viewLevelUrl = "http://localhost:12347/level";
    private String viewPracticeUrl = "http://localhost:12347/practice";
    private RequestSpecification requestSpecification;

    @Dado("^que Sergio quiere ver los datos de un nivel con un id invalido$")
    public void queSergioQuiereVerLosDatosDeUnNivelConUnIdInvalido() {
        this.viewLevelUrl += "/" + UUID.randomUUID().toString().substring(0, 8);
        this.requestSpecification = given().contentType("application/json");
    }

    @Dado("^que Sergio quiere ver los datos de todos los niveles$")
    public void queSergioQuiereVerLosDatosDeTodosLosNiveles() {
        this.requestSpecification = given().contentType("application/json");
    }

    @Dado("^que Sergio quiere ver los datos de una practica con un id invalido$")
    public void queSergioQuiereVerLosDatosDeUnaPracticaConUnIdInvalido() {
        this.viewPracticeUrl += "/" + UUID.randomUUID().toString().substring(0, 8);
        this.requestSpecification = given().contentType("application/json");
    }

    @Cuando("^realiza una peticion para ver el nivel$")
    public void realizaUnaPeticionParaVerElNivel() {
        this.requestSpecification.when().get(this.viewLevelUrl);
    }

    @Cuando("^realiza una peticion para ver los niveles$")
    public void realizaUnaPeticionParaVerLosNiveles() {
        this.requestSpecification.when().get(this.viewLevelUrl);
    }

    @Cuando("^realiza una peticion para solicitar la practica$")
    public void realizaUnaPeticionParaSolicitarLaPractica() {
        this.requestSpecification.when().get(this.viewPracticeUrl);
    }

    @Entonces("^el sistema retorna los niveles$")
    public void elSistemaRetornaLosNiveles() {
        List<String> response = then().extract().response().getBody().jsonPath().getList("");
        assertThat(response, is(notNullValue()));
    }
}
