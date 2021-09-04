package api_review;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static  io.restassured.RestAssured.*;
import static  org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

public class SpartanHelloTest {

    @Test
    public void helloTest(){
        //
        Response response = get("http://54.237.100.89:8000/api/hello");

        //get status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //get response at text
        System.out.println("response.asString() = " + response.asString());

        // print the response
        response.prettyPrint();

        assertEquals(response.statusCode(),200);
        assertEquals(response.asString(),"Hello from Sparta");

        //get and assert Content type header
        System.out.println(response.header("Content-Type"));

        //"text/plain;charset=UTF-8"
        assertEquals(response.header("Content-Type"),"text/plain;charset=UTF-8");


    }

    @Test
    public void HelloEndpointInMethodChain_Test(){
        //the power of restassured comes in method chaining
        //given some specification provided here like
        //url , parameters , body, header , authentication
        // when you send GET POST DELETE PATCH request to URL (endpoint) then verify assertion goes here with hamcrest matchers

        given()
                .baseUri("http://54.237.100.89:8000")
                .basePath("/api")
                .when().get("/hello").prettyPeek()
                .then().statusCode(200)
                .body(is("Hello from Sparta"))
                .header("Content-Type","text/plain;charset=UTF-8");
    }

    @Test
    public void HelloEndpointInMethodChain_Test2(){
        //the power of restassured comes in method chaining
        //given some specification provided here like
        //url , parameters , body, header , request log authentication
        //response log can go here
        // when you send GET POST DELETE PATCH request to URL (endpoint) then verify assertion goes here with hamcrest matchers

        given()
                .baseUri("http://54.237.100.89:8000")
                .basePath("/api")
                .log().all(). // this only log the request
                when()
                .get("/hello").  // .prettyPeek().
                then()
                .log().all()  // this only log the response
                .assertThat()
                .statusCode(200)
                .body( is("Hello from Sparta") )
                .header("Content-Type", is("text/plain;charset=UTF-8")  )
        ;
    }
}
