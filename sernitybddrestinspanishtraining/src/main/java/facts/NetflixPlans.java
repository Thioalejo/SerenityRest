package facts;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.facts.Fact;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.HashMap;
import java.util.List;

/*Los facts son hechos de un actor, o caracteristicas del actor que nos interesan a la hora de ejecutar el escenario de prueba
Por ejemplo, si un actor interactua con una aplicación como netflix y queremos saber que plan tenia en ese momento,
podemos con fact obtener los datos y mostrarlos en el reporte
permitiendo documentar mejor el contexto de la prueba*/
public class NetflixPlans implements Fact {
    private String plansInfo;
    public static NetflixPlans toViewSeries(){
        return new NetflixPlans();
    }
    @Override
    public void setup(Actor actor) {
        actor.attemptsTo(
                Get.resource("/plans")
        );
        List<HashMap<String, String>> plans = SerenityRest.lastResponse().path("data");
        actor.remember("plans", plans);
        plansInfo = plans.toString();
    }

    public String toString(){
        return "Los planes son " +plansInfo;
    }
}
