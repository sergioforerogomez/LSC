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
    private String viewLessonUrl = "http://localhost:12347/lesson";
    private RequestSpecification requestSpecification;

    @Dado("^que Sergio quiere ver los datos de todos los niveles$")
    public void queSergioQuiereVerLosDatosDeTodosLosNiveles() {
        this.requestSpecification = given().contentType("application/json");
    }

    @Cuando("^realiza una perticion para ver niveles$")
    public void realizaUnaPerticionParaVerNiveles() {
        this.requestSpecification.when().get(this.viewLevelUrl);
    }

    @Entonces("^el sistema retorna una lista de niveles$")
    public void elSistemaRetornaUnaListaDeNiveles() {
        List<Object> response = then().extract().response().getBody().jsonPath().getList("");
        assertThat(response, is(notNullValue()));
    }

    @Dado("^que Sergio quiere ver los datos de la leccion \"([^\"]*)\"$")
    public void queSergioQuiereVerLosDatosDeLaLeccion(String lesson) {
        this.viewLessonUrl += "/" + lesson;
        this.requestSpecification = given().contentType("application/json");
    }

    @Cuando("^realiza una perticion para ver lecciones$")
    public void realizaUnaPerticionParaVerLecciones() {
        this.requestSpecification.when().get(this.viewLessonUrl);
    }

    @Entonces("^el sistema retorna una lista de lecciones")
    public void elSistemaRetornaUnaListaDeLecciones() {
        List<Object> response = then().extract().response().getBody().jsonPath().getList("");
        assertThat(response, is(notNullValue()));
    }

    @Dado("^que Sergio quiere ver los datos de una leccion con un id invalido$")
    public void queSergioQuiereVerLosDatosDeUnaLeccionConUnIdInvalido() {
        this.viewLessonUrl += "/" + UUID.randomUUID().toString().substring(0, 8);
        this.requestSpecification = given().contentType("application/json");
    }
}
