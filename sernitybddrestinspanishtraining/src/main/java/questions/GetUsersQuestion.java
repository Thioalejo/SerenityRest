package questions;

import models.users.Users;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetUsersQuestion implements Question {


    @Override
    public Users answeredBy(Actor actor) {
        //Para que haga el mapeo llene las propiedades y nos devuelve un objeto tipo Users
        return SerenityRest.lastResponse().as(Users.class);
    }
}
