package rest_test;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redmine.model.dto.UserDto;
import redmine.model.dto.UserInfo;
import redmine.model.user.User;
import redmine.utils.gson.GsonHelper;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserGetByUserRestApiTest {
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
    public void getUserTest() {
        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", byUser.getApiKey())
                .request(Method.GET, String.format("users/%d.json", byUser.getId()));
        UserInfo responseUser = GsonHelper.getGson().fromJson(response.getBody().asString(), UserDto.class).getUser();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseUser.getId(), byUser.getId());
        Assert.assertEquals(responseUser.getLogin(), byUser.getLogin());
        Assert.assertEquals(responseUser.getFirstname(), byUser.getFirstname());
        Assert.assertEquals(responseUser.getLastname(), byUser.getLastname());
        Assert.assertFalse(responseUser.getAdmin());
        Assert.assertNotNull(responseUser.getApiKey());
    }


    @Test
    public void getOtherUserTest() {
        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", byUser.getApiKey())
                .request(Method.GET, String.format("users/%d.json", user.getId()));
        UserInfo responseUser = GsonHelper.getGson().fromJson(response.getBody().asString(), UserDto.class).getUser();
        log.debug("responseUser: {}", responseUser);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseUser.getId(), user.getId());
        Assert.assertEquals(responseUser.getLogin(), user.getLogin());
        Assert.assertEquals(responseUser.getFirstname(), user.getFirstname());
        Assert.assertEquals(responseUser.getLastname(), user.getLastname());
        Assert.assertNull(responseUser.getAdmin());
        Assert.assertNull(responseUser.getApiKey());
    }

}

