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
import redmine.utils.StringGenerators;
import redmine.utils.gson.GsonHelper;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserDeleteByUserRestApiTest {
    private static final String adminApiKey = "f02b2da01a468c4116be898911481d1b928c15f9";
    private static UserInfo user;
    private static UserInfo otherUser;


    @BeforeClass
    public static void init() {
        user = generateUser();
        otherUser = generateUser();
    }

    @AfterClass
    public static void clear() {
        deleteUser(user);
        deleteUser(otherUser);
    }

    @Test
    public void deleteOtherUserTest() {
        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", user.getApiKey())
                .request(Method.DELETE, String.format("users/%d.json", otherUser.getId()));
        Assert.assertEquals(response.getStatusCode(), 403);
    }

    @Test
    public void deleteUserTest() {
        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", user.getApiKey())
                .request(Method.DELETE, String.format("users/%d.json", user.getId()));
        Assert.assertEquals(response.getStatusCode(), 403);
    }


    private static UserInfo generateUser() {
        String login = StringGenerators.randomEnglishLowerString(8);
        String firstName = StringGenerators.randomEnglishLowerString(8);
        String lastName = StringGenerators.randomEnglishLowerString(8);
        String mail = StringGenerators.randomEmail();
        String password = StringGenerators.randomEnglishLowerString(8);
        String body = String.format("{\n" +
                "    \"user\": {\n" +
                "        \"login\": \"%s\",\n" +
                "        \"firstname\": \"%s\",\n" +
                "        \"lastname\": \"%s\",\n" +
                "        \"mail\": \"%s\",\n" +
                "        \"password\": \"%s\"\n" +
                "    }\n" +
                "}", login, firstName, lastName, mail, password);
        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", adminApiKey)
                .body(body)
                .request(Method.POST, "users.json");
        return GsonHelper.getGson().fromJson(response.getBody().asString(), UserDto.class).getUser();
    }

    private static void deleteUser(UserInfo user) {
        given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", adminApiKey)
                .request(Method.DELETE, String.format("users/%d.json", user.getId()));
    }
}

