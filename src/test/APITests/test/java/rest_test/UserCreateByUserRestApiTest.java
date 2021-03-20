package rest_test;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redmine.model.user.User;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserCreateByUserRestApiTest {
    private User byUser;

    @BeforeClass
    void init(){
        byUser = new User().create();
    }

    @AfterClass
    void drop(){
        byUser.delete();
    }


    @Test
    public void createUserTest() {
        User user = new User();
        String body = String.format("{\n" +
                "    \"user\": {\n" +
                "        \"login\": \"%s\",\n" +
                "        \"firstname\": \"%s\",\n" +
                "        \"lastname\": \"%s\",\n" +
                "        \"mail\": \"%s\",\n" +
                "        \"password\": \"%s\"\n" +
                "    }\n" +
                "}", user.getLogin(), user.getFirstname(), user.getLastname(), user.getMail(), user.getPassword());
        log.debug("body: {}", body);
        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", byUser.getApiKey())
                .body(body)
                .request(Method.POST, "users.json");

        Assert.assertEquals(response.getStatusCode(), 403);
    }
}
