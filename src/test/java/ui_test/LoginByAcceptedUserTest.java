package ui_test;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redmine.model.dto.UserDto;
import redmine.model.dto.UserInfo;
import redmine.model.user.User;
import redmine.ui.pages.LoginPage;
import redmine.utils.StringGenerators;
import redmine.utils.gson.GsonHelper;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

@Slf4j
public class LoginByAcceptedUserTest {
    private static final String adminApiKey = "f02b2da01a468c4116be898911481d1b928c15f9";

    private WebDriver webDriver;
    private UserInfo admin;

    @BeforeClass
    void init() {
        admin = generateNotAcceptedUser();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @AfterClass
    void quite() {
        //webDriver.quit();
        deleteUser(admin);
    }

    @Test
    void createUserTest(){
        User user = createAcceptedUser();
        System.out.printf("login: %s\n", user.getLogin());
        System.out.printf("password: %s\n", user.getPassword());
        Assert.assertTrue(true);
    }

    @Test
    void login() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(admin.getLogin(), admin.getPassword());
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/my/page");
        WebElement loginAs = webDriver.findElement(By.xpath("//div[@id='loggedas']"));
        Assert.assertEquals(loginAs.getText(), "Вошли как " + admin.getLogin());
        List<WebElement> topMenuElements = webDriver.findElements(By.xpath("//div[@id='top-menu']//li"));
        List<String> topMenuTexts = topMenuElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Assert.assertTrue(topMenuTexts.contains("Домашняя страница"));
        Assert.assertTrue(topMenuTexts.contains("Моя страница"));
        Assert.assertTrue(topMenuTexts.contains("Проекты"));
        Assert.assertTrue(topMenuTexts.contains("Администрирование"));
        Assert.assertTrue(topMenuTexts.contains("Помощь"));
        Assert.assertTrue(topMenuTexts.contains("Моя учётная запись"));
        Assert.assertTrue(topMenuTexts.contains("Выйти"));
        Assert.assertFalse(topMenuTexts.contains("Войти"));
        Assert.assertFalse(topMenuTexts.contains("Регистрация"));
        WebElement search = webDriver.findElement(By.xpath("//label[@for='q']"));
        Assert.assertEquals(search.getText(), "Поиск:");
    }

    private static User createAcceptedUser(){
       return new User().create();
    }

    private static UserInfo generateNotAcceptedUser() {
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
                "        \"password\": \"%s\",\n" +
                "        \"status\": %d\n" +
                "    }\n" +
                "}", login, firstName, lastName, mail, password, 2);
        Response response = given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", adminApiKey)
                .body(body)
                .request(Method.POST, "users.json");
        UserInfo userInfo = GsonHelper.getGson().fromJson(response.getBody().asString(), UserDto.class).getUser();
        log.debug("userInfo: {}", userInfo);
        return userInfo.setPassword(password);
    }

    private static void deleteUser(UserInfo user) {
        given()
                .baseUri("http://edu-at.dfu.i-teco.ru/")
                .contentType(ContentType.JSON)
                .header("X-Redmine-API-Key", adminApiKey)
                .request(Method.DELETE, String.format("users/%d.json", user.getId()));
    }
}
