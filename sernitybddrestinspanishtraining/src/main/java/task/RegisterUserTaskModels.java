package task;

import interactions.Post;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class RegisterUserTaskModels implements Task {

    private final Object userInfo;

    public RegisterUserTaskModels(Object userInfo){
        this.userInfo = userInfo;
    }

    public static Performable withInfo(Object userInfo){
        return instrumented(RegisterUserTaskModels.class, userInfo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/register").with(
                        requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(userInfo)
                )
        );
    }
}
