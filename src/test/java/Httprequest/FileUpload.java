package Httprequest;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class FileUpload {
    @Test
    public void single(){
        File file=new File("C:\\Users\\Shiva\\Documents\\capture.txt");

        given()
                .multiPart("file",file)
                .contentType("multipart/form-data")

                .when().post("https://www.filemail.com/share/upload-file")

                .then()
                .statusCode(200)
                .headers("Content-Type","text/html; charset=utf-8");

    }
    @Test
    public void multifiles(){
        File file=new File("C:\\Users\\Shiva\\Documents\\capture.txt");
        File file1=new File("C:\\Users\\Shiva\\Documents\\basics.txt");
        given()
                .multiPart("files",file)
                .multiPart("files",file1)
                .contentType("multipart/form-data")

                .when().post("https://www.filemail.com/share/upload-file")

                .then()
                .statusCode(200)
                .headers("Content-Type","text/html; charset=utf-8");



    }
    @Test
    public void multifiles1(){
        File file=new File("C:\\Users\\Shiva\\Documents\\capture.txt");
        File file1=new File("C:\\Users\\Shiva\\Documents\\basics.txt");
        File filearr[]={file,file1};
        given()
                .multiPart("files",filearr)
                .contentType("multipart/form-data")

                .when().post("https://www.filemail.com/share/upload-file")

                .then()
                .statusCode(200)
                .headers("Content-Type","text/html; charset=utf-8");



    }
}
