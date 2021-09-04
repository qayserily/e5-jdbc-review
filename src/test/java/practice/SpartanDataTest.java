package practice;

import io.restassured.http.ContentType;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

public class SpartanDataTest {

    // we want all the test in this class use same base url same base path
    @BeforeClass
    public void setUp(){
        baseURI = "http://54.237.100.89:8000";
        basePath= "/api";
    }

    @Test
    public void TestOneSpartan(){
        // Send get request to /sspartans/{id} by providing valid id

        // verify the status code , content type , and actual data
        given().pathParam("id",12)
                .log().all()
                .accept(ContentType.JSON) // telling the server you want json
        .when()
                .get("/spartans/{id}")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", is(equalTo(12))) // "id" here is jsonpath to get id from response json
                .body("name", is("Sol"));

    }

    //optionally reset above 2 values after you are done with this class
    @AfterClass
    public void cleanUp(){
        reset(); // it will set above 2 values in @BeforeClass to it's original value
    }
}
