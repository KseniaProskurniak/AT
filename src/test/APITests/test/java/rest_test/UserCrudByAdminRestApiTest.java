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
import redmine.model.user.Admin;
import redmine.model.user.User;
import redmine.utils.StringGenerators;
import redmine.utils.gson.GsonHelper;

import java.util.Optional;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserCrudByAdminRestApiTest {

    private User byAdmin;
    private User user;

    @BeforeClass
    void init() {
        byAdmin = new Admin().create();
        user = new User();
    }

    @AfterClass
    void drop() {
        byAdmin.delete();
    }

    @Test
    public void createUserTest() {
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
                .header("X-Redmine-API-Key", byAdmin.getApiKey())
                .body(body)
                .request(Method.POST, "users.json");

        Assert.assertEquals(response.getStatusCode(), 201);
        String responseBody = response.getBody().asString();
        log.debug("responseBody: {}", responseBody);
        UserDto createdUser = GsonHelper.getGson().fromJson(responseBody, UserDto.class);
        log.debug("createdUser: {}", createdUser);
        Assert.assertEquals(createdUser.getUser().getLogin(), user.getLogin());
        Assert.assertEquals(createdUser.getUser().getFirstname(), user.getFirstname());
        Assert.assertEquals(createdUser.getUser().getLastname(), user.getLastname());
        Assert.assertEquals(createdUser.getUser().getMail(), user.getMail());
        Assert.assertNull(createdUser.getUser().getLastLoginOn());
        Assert.assertEquals(createdUser.getUser().getStatus().intValue(), 1);
        Assert.assertFalse(createdUser.getUser().getAdmin());
        user.setId(createdUser.getUser().getId());
    }

    @Test(dependsOnMethods = "createUserTest")
    public void createTwiceUserTest() {
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
                .header("X-Redmine-API-Key", byAdmin.getApiKey())
                .body(body)
                .request(Method.POST, "users.json");

        Assert.assertEquals(response.getStatusCode(), 422);
        String responseBody = response.getBody().asString();
        log.debug("responseBody: {}", responseBody);
        Assert.assertTrue(responseBody.contains("errors"));
        Assert.assertTrue(responseBody.contains("Email уже существует"));
        Assert.assertTrue(responseBody.contains("Пользователь уже существует"));
    }

    @Test
    public void createUserWithInvalidEmailAndPasswordTest() {
        String invalidEmail = StringGenerators.randomEnglishLowerString(4);
        String invalidPassword = StringGenerators.randomEnglishLowerString(4);
        String body = String.format("{\n" +
                "    \"user\": {\n" +
                "        \"login\": \"%s\",\n" +
                "        \"firstname\": \"%s\",\n" +
                "        \"lastname\": \"%s\",\n" +
                "        \"mail\": \"%s\",\n" +
                "        \"password\": \"%s\"\n" +
                "    }\n" +
                "}", user.getLogin(), user.getFirstname(), user.getLastname(), invalidEmail, invalidPassword);
        log.debug("body: {}", body);

        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", byAdmin.getApiKey())
                .body(body)
                .request(Method.POST, "users.json");

        Assert.assertEquals(response.getStatusCode(), 422);
        String responseBody = response.getBody().asString();
        log.debug("responseBody: {}", responseBody);
        Assert.assertTrue(responseBody.contains("errors"));
        Assert.assertTrue(responseBody.contains("Email имеет неверное значение"));
        Assert.assertTrue(responseBody.contains("Пароль недостаточной длины (не может быть меньше 8 символа)"));
    }


    @Test(dependsOnMethods = "createUserTest")
    public void updateUserWithStatusTest() {
        String body = String.format("{\n" +
                "    \"user\": {\n" +
                "        \"login\": \"%s\",\n" +
                "        \"firstname\": \"%s\",\n" +
                "        \"lastname\": \"%s\",\n" +
                "        \"mail\": \"%s\",\n" +
                "        \"password\": \"%s\",\n" +
                "        \"status\": %d\n" +
                "    }\n" +
                "}", user.getLogin(), user.getFirstname(), user.getLastname(), user.getMail(), user.getPassword(), 2);

        log.debug("body: {}", body);

        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", byAdmin.getApiKey())
                .body(body)
                .request(Method.PUT, String.format("users/%d.json", user.getId()));
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    @Test(dependsOnMethods = "updateUserWithStatusTest")
    public void getUserTest() {
        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", byAdmin.getApiKey())
                .request(Method.GET, String.format("users/%d.json", user.getId()));
        UserDto user = GsonHelper.getGson().fromJson(response.getBody().asString(), UserDto.class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(Optional.ofNullable(user.getUser().getStatus()), Optional.of(2));
    }

    @Test(dependsOnMethods = "getUserTest")
    public void deleteUserTest() {
        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", byAdmin.getApiKey())
                .request(Method.DELETE, String.format("users/%d.json", user.getId()));
        Assert.assertEquals(response.getStatusCode(), 204);
    }

    @Test(dependsOnMethods = "deleteUserTest")
    public void deleteTwiceUserTest() {
        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", byAdmin.getApiKey())
                .request(Method.DELETE, String.format("users/%d.json", user.getId()));
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
