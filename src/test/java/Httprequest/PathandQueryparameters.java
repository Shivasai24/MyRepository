package Httprequest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class PathandQueryparameters {
    @Test
    public void paramter(){
        given()
                .pathParams("mypath","users")
                .queryParam("page",2)
                .when()
                .get("https://reqres.in/api/{mypath}")
                .then()
                .statusCode(200)
                .log().all();

    }
}
