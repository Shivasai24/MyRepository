package Httprequest;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class Httprequests {
    int id;
    @Test(priority=1)
    public void getusers(){

        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .log().all();
    }
    @Test(priority = 2)
    public void createuser(){
        HashMap hm=new HashMap();
        hm.put("name","Shiva");
        hm.put("job","QA Tester");
        id=given()
                .contentType("application/json")
                .body(hm)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
//                .then()
//                .statusCode(201)
//                .log().all();
    }
@Test(priority = 3,dependsOnMethods = {"createuser"})
    public void updateuser(){
    HashMap hm=new HashMap();
    hm.put("name","Shivasai");
    hm.put("job","QA Auto");
    given()
            .contentType("application/json")
            .body(hm)
            .when()
            .put("https://reqres.in/api/users/"+id)
             .then()
                .statusCode(200)
                .log().all();
    }
    @Test(priority = 4)
    public void deleteUser(){
        given()
                .when()
                .delete("https://reqres.in/api/users/"+id)
                .then()
                .statusCode(204)
                .log().all();
    }
}
