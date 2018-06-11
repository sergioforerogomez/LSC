package com.lsc.users.glue;

import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;

import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GeneralSteps {
    @Y("^status code \"([^\"]*)\"$")
    public void statusCode(String statusCode) {
        String responseStatusCode = String.valueOf(then().extract().statusCode());
        assertThat(responseStatusCode, equalTo(statusCode));
    }

    @Entonces("^el sistema retorna \"([^\"]*)\"$")
    public void elSistemaRetorna(String errorMessage) {
        Object responseErrorMessage = then().extract().response().getBody().jsonPath().get("errorMessage");
        assertThat(responseErrorMessage, equalTo(errorMessage));
    }

    @Entonces("^el sistema retorna un token$")
    public void elSistemaUnRetornaToken() {
        Object responseToken = then().extract().response().getBody().jsonPath().get("token");
        assertThat(responseToken, is(notNullValue()));
    }

    @Y("^el id del perfil$")
    public void elIdDelPerfil() {
        String responseProfileId = then().extract().response().getBody().jsonPath().get("profileId");
        assertThat(responseProfileId, is(notNullValue()));
    }

    @Entonces("^el sistema retorna status code \"([^\"]*)\"$")
    public void retornaStatusCode(String statusCode) {
        Object responseStatusCode = String.valueOf(then().extract().statusCode());
        assertThat(responseStatusCode, equalTo(statusCode));
    }
}
