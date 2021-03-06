package com.lsc.common.glue;

import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;

import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GeneralSteps {
    @Y("^status code \"([^\"]*)\"$")
    public void statusCode(String statusCode) {
        Object responseStatusCode = String.valueOf(then().extract().statusCode());
        assertThat(responseStatusCode, equalTo(statusCode));
    }

    @Entonces("^el sistema retorna \"([^\"]*)\"$")
    public void elSistemaRetorna(String errorMessage) {
        Object responseErrorMessage = then().extract().response().getBody().jsonPath().get("errorMessage");
        assertThat(responseErrorMessage, equalTo(errorMessage));
    }
}
