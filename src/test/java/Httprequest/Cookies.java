package Httprequest;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class Cookies {
    @Test
    public void cookie(){
      Response res= given()
                .when()
                .get("https://www.google.com/");

      String cookie=res.getCookie("AEC");
      System.out.println(cookie);
    }
    @Test
    public void cookies(){
        Response res=given()
                .when()
                .get("https://www.google.com/");

        Map<String,String> cookies=res.getCookies();
        //System.out.println(cookies.keySet());
        for(String p:cookies.keySet()){
            String cookiesv=res.getCookie(p);
            System.out.println(p+"   "+cookiesv);
        }

        }
        @Test
        public void headers(){
        given().when().get("https://www.google.com/").then()
                .headers("Content-Type","text/html; charset=ISO-8859-1")
                .headers("Content-Encoding","gzip")
                .headers("Server","gws")
                .log().all();

        }

        @Test
        public void singleheader(){
        Response res=given().when().get("https://www.google.com/");

       String hd= res.getHeader("Content-Type");
       System.out.println(hd);

//         Headers headers=res.getHeaders();
//         for (Header l:headers){
//             System.out.println(l.getName()+"  "+l.getValue());
//         }

        }
        @Test
        public void logg(){
            given().when().get("https://www.google.com/")
                    .then()
                    .log().body()
                    .log().cookies()
                    .log().headers();


        }

    }

