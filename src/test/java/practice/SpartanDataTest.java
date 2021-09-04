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

    //GET http://54.237.100.89:8000/api/spartans
    //specify we want json result back
    //verify the first item name is "Meade"
    // verify second item id equal to 2
    // verify the size of this array is

    /*
    {
        "id": 1,
        "name": "Meade",
        "gender": "Male",
        "phone": 3584128232
    },
    {
        "id": 2,
        "name": "Nels",
        "gender": "Male",
        "phone": 4218971348
    },
     */
    @Test
    public void testAllSpartans(){

        given()
                .accept(ContentType.JSON).
                when()
                .get("/spartans").
                then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                // name[0] is json path to get first json object name first
                .body("name[0]" , is("Meade"))
                .body("id[1]" , equalTo(2))
                // I WANT TO VERİFY YHE SIZE OF JSON ARRAY IS 183
                // jsonPath t oget your entire json array is "" empty String
                .body("" , hasSize(107)) ;

    }
    // https://54.237.100.89:8000/api/spartans/search?nameContains=a&gender=Female
    @Test
    public void testSearch(){

        given()
                .log().all()
                .queryParam("nameContains","a")
                .queryParam("gender","Female").
                when()
                .get("/spartans/search").
                then()
                .log().all()
                .assertThat()
                .statusCode(200)
                // verify the totalElement field value is 33 (at this moment)
                .body("totalElement" , equalTo(33) )
                // verify the first element name is Paige
                .body("content[0].name" , is("Paige") )
                // second element phone number is 3312820936
                .body("content.phone[1]" , equalTo(3312820936L) )
                // Check the size of entire json array and assert it has size of 33
                .body("content", hasSize(33) ) ;


    }

    // GET http://54.237.100.89:8000/api/spartans
    // specify we want xml result back
    // verify we got xml back
    @Test
    public void testXmlResponseType(){

        given()
                .accept(ContentType.XML).
                when()
                .get("/spartans").
                then()
                .assertThat()
                .statusCode(200) // status code: 406
                .contentType(ContentType.XML) ;

    }


    //optionally reset above 2 values after you are done with this class
    @AfterClass
    public void cleanUp(){
        reset(); // it will set above 2 values in @BeforeClass to it's original value
    }

}
