package rest_test;


import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import redmain.model.dto.UserDto;
import redmain.utils.StringGenerators;
import redmain.utils.gson.GsonHelper;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@Slf4j
public class MyFirstRestTest {
    private static final String apiKey = "f02b2da01a468c4116be898911481d1b928c15f9";
    private static final String login = StringGenerators.randomEnglishLowerString(8);
    private static final String firstName = StringGenerators.randomEnglishLowerString(12);
    private static final String lastName = StringGenerators.randomEnglishLowerString(12);
    private static final String mail = StringGenerators.randomEmail();
    private static final String password = StringGenerators.randomEnglishLowerString(8);

    @Test
    public void RestRequestTest() {
        given().baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .request(Method.GET, "roles.json")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(containsString("name"));
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
                "}", login, firstName, lastName, mail, password);
        log.debug("body: {}", body);
        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", apiKey)
                .body(body)
                .request(Method.POST, "users.json");

        Assert.assertEquals(response.getStatusCode(), 201);
        String responseBody = response.getBody().asString();
        log.debug("responseBody: {}", responseBody);
        UserDto createdUser = GsonHelper.getGson().fromJson(responseBody, UserDto.class);
        log.debug("createdUser: {}", createdUser);
        Assert.assertEquals(createdUser.getUser().getLogin(), login);
        Assert.assertEquals(createdUser.getUser().getFirstname(), firstName);
        Assert.assertEquals(createdUser.getUser().getLastname(), lastName);
        Assert.assertEquals(createdUser.getUser().getMail(), mail);
        Assert.assertNull(createdUser.getUser().getLastLoginOn());
        Assert.assertEquals(createdUser.getUser().getStatus().intValue(), 1);
        Assert.assertFalse(createdUser.getUser().getAdmin());
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
                "}", login, firstName, lastName, mail, password);
        log.debug("body: {}", body);

        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", apiKey)
                .body(body)
                .request(Method.POST, "users.json");

        Assert.assertEquals(response.getStatusCode(), 422);
        String responseBody = response.getBody().asString();
        log.debug("responseBody: {}", responseBody);
        Assert.assertTrue(responseBody.contains("errors"));
        Assert.assertTrue(responseBody.contains("Email уже существует"));
        Assert.assertTrue(responseBody.contains("Пользователь уже существует"));

    }
}
