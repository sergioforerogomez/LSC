package com.lsc.dictionary.glue;

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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class DictionarySteps {
    private String dictionaryUrl = "http://localhost:12348/word";
    private RequestSpecification requestSpecification;

    @Dado("^que Sergio quiere ver los datos de todas las palabras$")
    public void queSergioQuiereVerLosDatosDeTodasLasPalabras() {
        this.requestSpecification = given().contentType("application/json");
    }

    @Cuando("^realiza una peticion para solicitar palabras$")
    public void realizaUnaPeticionParaSolicitarLasPalabras() {
        this.requestSpecification.when().get(this.dictionaryUrl);
    }

    @Entonces("^el sistema retorna las palabras$")
    public void elSistemaRetornaLasPalabras() {
        List<Object> response = then().extract().response().getBody().jsonPath().getList("");
        assertThat(response, is(notNullValue()));
    }

    @Dado("^que Sergio quiere ver los datos de una palabra existente$")
    public void queSergioQuiereVerLosDatosDeUnaPalabraExistente() {
        this.dictionaryUrl += "/Yo";
        this.requestSpecification = given().contentType("application/json");
    }

    @Y("^\"([^\"]*)\" de la plabra$")
    public void deLaPlabra(String attribute) {
        Object response = then().extract().response().getBody().jsonPath().get(attribute);
        assertThat(response, is(notNullValue()));
    }

    @Dado("^que Sergio quiere ver los datos de una palabra inexistente$")
    public void queSergioQuiereVerLosDatosDeUnaPalabraInexistente() {
        this.dictionaryUrl += "/" + UUID.randomUUID().toString().substring(0, 8);
        this.requestSpecification = given().contentType("application/json");
    }
}
