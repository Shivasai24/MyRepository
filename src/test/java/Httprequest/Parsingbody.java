package Httprequest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Parsingbody{
    @Test
    public void parsing(){
        given().when().get("https://reqres.in/api/users/2").then()
                .statusCode(200)
                .body("data.last_name",equalTo("Weaver"));
         }
         @Test
         public void Another(){
        Response res= given().contentType(ContentType.JSON)
             .when().get("https://reqres.in/api/users/2");
            Assert.assertEquals(res.getStatusCode(),200);
            Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
            Assert.assertEquals( res.jsonPath().get("data.last_name").toString(),"Weaver");
         }

    @Test
    public void Other(){
        Response res= given().contentType(ContentType.JSON)
                .when().get("https://reqres.in/api/users");
        JSONObject jo=new JSONObject(res.asString());
        boolean status=false;
        for(int i=0;i<jo.getJSONArray("data").length();i++){
            String firstname=jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
            if(firstname.equals("Emma")){
                status=true;
                break;
            }
        }
        Assert.assertEquals(status,true);

    }

}
