package Httprequest;


import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class PostData {
    int id;
//@Test(priority = 1)
   public void usinghashmap() {
  HashMap data = new HashMap();
        data.put("name","Shiva");
        data.put("job","QA Auto");

        id=given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
//                .then()
//                .statusCode(201)
//                .body("name",equalTo("Shiva"))
//                .body("job",equalTo("QA Auto"))
//                .log().all();
    }
//    @Test
    public void usingjson() {
        JSONObject js = new JSONObject();
        js.put("name","Shiva");
        js.put("job","QA Tester");
         given()
                .contentType("application/json")
                .body(js.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                 .statusCode(201)
                 .body("name",equalTo("Shiva"))
                 .body("job",equalTo("QA Tester"))
                 .log().all();
    }
//    @Test
    public void usingpojo() {
        Pojopostdata pd=new Pojopostdata();
        pd.setName("Shiva");
        pd.setJob("QA Tester");
        given()
                .contentType("application/json")
                .body(pd)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("Shiva"))
                .body("job",equalTo("QA Tester"))
                .log().all();
    }
    @Test
    public void usingexternal() throws FileNotFoundException {
        File f=new File(".\\body.json");
        FileReader fr= new FileReader(f);
        JSONTokener jt=new JSONTokener(fr);
        JSONObject jo= new JSONObject(jt);
        given()
                .contentType("application/json")
                .body(jo.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("Shiva"))
                .body("job",equalTo("QA Tester"))
                .log().all();
    }
//    @Test(priority = 2)
   public void delete(){
    given()
            .when()
            .delete("https://reqres.in/api/users/"+id)
            .then()
            .statusCode(204);
    }
}
