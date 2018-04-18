package com.lsc.dictionary.glue;

import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;

import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GeneralSteps {
    @Entonces("^el sistema retorna \"([^\"]*)\"$")
    public void elSistemaRetorna(String errorMessage) {
        String responseErrorMessage = then().extract().response().getBody().jsonPath().get("errorMessage");
        assertThat(responseErrorMessage, equalTo(errorMessage));
    }

    @Y("^status code \"([^\"]*)\"$")
    public void statusCode(String statusCode) {
        String responseStatusCode = String.valueOf(then().extract().statusCode());
        assertThat(responseStatusCode, equalTo(statusCode));
    }

    @Entonces("^el sistema retorna status code \"([^\"]*)\"$")
    public void retornaStatusCode(String statusCode) {
        String responseStatusCode = String.valueOf(then().extract().statusCode());
        assertThat(responseStatusCode, equalTo(statusCode));
    }
}
