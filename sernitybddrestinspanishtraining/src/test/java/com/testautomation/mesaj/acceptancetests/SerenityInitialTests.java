package com.testautomation.mesaj.acceptancetests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SerenityRunner.class)
public class SerenityInitialTests {

    private  static final String URL_BASE = "http://localhost:5000/api";

    @Test
    public void  getUsers(){
        Actor alejandro = Actor.named("Alejandro the QA")
                .whoCan(CallAnApi.at(URL_BASE));

        alejandro.attemptsTo(Get.resource("/users?page=2"));

        assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);
    }

    @Test
    public void  getUsersFail(){
        Actor alejandro = Actor.named("Alejandro the QA")
                .whoCan(CallAnApi.at(URL_BASE));

        alejandro.attemptsTo(Get.resource("/users?page=2"));

        assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(400);
    }
}
