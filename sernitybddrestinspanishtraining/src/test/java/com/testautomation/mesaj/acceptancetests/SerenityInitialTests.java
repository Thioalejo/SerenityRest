package com.testautomation.mesaj.acceptancetests;

import facts.NetflixPlans;
import models.users.Datum;
import models.users.Register;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.junit.Test;
import org.junit.runner.RunWith;
import questions.GetUsersQuestion;
import questions.ResponseCode;
import task.GetUsersTask;
import task.RegisterUserTaskModels;
import task.RegisterUserTaskString;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SerenityRunner.class)
public class SerenityInitialTests {

    private  static final String URL_BASE = "http://localhost:5000/api";

    @Test
    public void  getUsers(){
        Actor alejandro = Actor.named("Alejandro the QA")
                .whoCan(CallAnApi.at(URL_BASE));

        //Llamamos la pagina 1
        //Task
        alejandro.attemptsTo(GetUsersTask.fromPage(1));
        /*alejandro.attemptsTo(Get.resource("/users?page=2")
                .with(requestSpecification -> requestSpecification.contentType(ContentType.JSON)
                        .header("header1", "value1")));*/

       // assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);
        //Validamos que responda 200
        //Question
        alejandro.should(seeThat("el codigo de respuesta", ResponseCode.was(),equalTo(200)));

        Datum user = new GetUsersQuestion().answeredBy(alejandro).getData().stream().filter(x->x.getId() == 1).findFirst().orElse(null);

        alejandro.should(seeThat("el usuario no es nulo", act-> user, notNullValue()));

        alejandro.should(
                seeThat("el email del usuario", act-> user.getEmail(), equalTo("george.bluth@reqres.in")),
                seeThat("el avatar del usuario", act-> user.getAvatar(), equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg")));
    }

    @Test
    public void  getUsersFail(){
        Actor alejandro = Actor.named("Alejandro the QA")
                .whoCan(CallAnApi.at(URL_BASE));

        alejandro.attemptsTo(Get.resource("/users?page=2"));

        assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(400);
    }

    @Test
    public void  registerUserTestWithString(){
        Actor alejandro = Actor.named("Alejandro the QA")
                .whoCan(CallAnApi.at(URL_BASE));

        //Example with String
        String registerUserInfo = "{\n" +
                "    \"name\":\"morpheus\",\n" +
                "    \"Job\":\"Leader\",\n" +
                "    \"email\":\"tobias.funke@reqres.in\",\n" +
                "    \"password\":\"serenity\"\n" +
                "}";
        alejandro.attemptsTo(RegisterUserTaskString.withInfo(registerUserInfo));

        alejandro.should(seeThat("el codigo de respuesta", ResponseCode.was(),equalTo(200)));

    }

    @Test
    public void  registerUserTestWithModel(){
        Actor alejandro = Actor.named("Alejandro the QA")
                .whoCan(CallAnApi.at(URL_BASE));

        //Example with Models
        Register register = new Register();
        register.setName("morpheus");
        register.setJob("Leader");
        register.setEmail("tobias.funke@reqres.in");
        register.setPassword("serenity");

        alejandro.attemptsTo(RegisterUserTaskModels.withInfo(register));

        alejandro.should(seeThat("el codigo de respuesta", ResponseCode.was(),equalTo(200)));

    }

    @Test
    public void  facTest(){
        Actor alejandro = Actor.named("Alejandro the QA")
                .whoCan(CallAnApi.at(URL_BASE));

        //Alejandro tiene determinado plan de netflix, para ver ciertas series
       alejandro.has(NetflixPlans.toViewSeries());

    }

}
