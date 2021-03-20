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
public class UserDeleteByUserRestApiTest {
    private User byUser;
    private User user;

    @BeforeClass
    void init() {
        byUser = new User().create();
        user = new User().create();
    }

    @AfterClass
    void drop() {
        byUser.delete();
        user.delete();
    }

    @Test
    public void deleteOtherUserTest() {
        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", byUser.getApiKey())
                .request(Method.DELETE, String.format("users/%d.json", user.getId()));
        Assert.assertEquals(response.getStatusCode(), 403);
    }

    @Test
    public void deleteUserTest() {
        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", byUser.getApiKey())
                .request(Method.DELETE, String.format("users/%d.json", byUser.getId()));
        Assert.assertEquals(response.getStatusCode(), 403);
    }
}

