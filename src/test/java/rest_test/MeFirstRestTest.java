package rest_test;


import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MeFirstRestTest {
    @Test
    public void RestRequestTest(){
        given().baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .request(Method.GET, "roles_json")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }
}
