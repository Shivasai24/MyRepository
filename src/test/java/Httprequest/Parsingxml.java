package Httprequest;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class Parsingxml {
    @Test
    public void xml(){
        given().when().get("http://restapi.adequateshop.com/api/Traveler?page=1")
                .then().statusCode(200)
                .headers("Content-Type","application/xml; charset=utf-8")
                .body("TravelerinformationResponse.page",equalTo("1"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[1].name",equalTo("AS"));
    }
    @Test
    public void approach(){
        Response res=given().when().get("http://restapi.adequateshop.com/api/Traveler?page=1");

        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.getHeader("Content-Type"),"application/xml; charset=utf-8");
        Assert.assertEquals(res.xmlPath().get("TravelerinformationResponse.page").toString(),"1");
        Assert.assertEquals(res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[1].name").toString(),"AS");
    }
    @Test
    public void xmlbody(){
        Response res=given().when().get("http://restapi.adequateshop.com/api/Traveler?page=1");
        XmlPath xp=new XmlPath(res.asString());
        List<String> travellers=xp.getList("TravelerinformationResponse.travelers.Travelerinformation");
        Assert.assertEquals(travellers.size(),10);

        List<String> travellersnames=xp.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
        boolean status=false;
        for(String traveller:travellersnames){
          if(traveller.equals("Ashor")){
              System.out.println("IS there");
              status=true;
              break;
          }
       }
        Assert.assertEquals(status,true);
    }
}
