package rest_test;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import redmine.utils.StringGenerators;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserCreateByUserRestApiTest {
    private static final String userApiKey = "b9008355a7ed9c1c35abdb3bbff1e54956c2c2da";
    private static final String login = StringGenerators.randomEnglishLowerString(8);
    private static final String firstName = StringGenerators.randomEnglishLowerString(12);
    private static final String lastName = StringGenerators.randomEnglishLowerString(12);
    private static final String mail = StringGenerators.randomEmail();
    private static final String password = StringGenerators.randomEnglishLowerString(8);


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
                .header("X-Redmine-API-Key", userApiKey)
                .body(body)
                .request(Method.POST, "users.json");

        Assert.assertEquals(response.getStatusCode(), 403);
    }
}
