package ui_test;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redmine.model.user.User;
import redmine.ui.pages.LoginPage;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class LoginByAcceptedUserTest {

    private WebDriver webDriver;
    private User user;

    @BeforeClass
    void init() {
        user = generateAcceptedUser();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @AfterClass
    void quite() {
        //webDriver.quit();
        deleteUser(user);
    }


    @Test
    void login() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(user.getLogin(), user.getPassword());
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/my/page");
        WebElement loginAs = webDriver.findElement(By.xpath("//div[@id='loggedas']"));
        Assert.assertEquals(loginAs.getText(), "Вошли как " + user.getLogin());
        List<WebElement> topMenuElements = webDriver.findElements(By.xpath("//div[@id='top-menu']//li"));
        List<String> topMenuTexts = topMenuElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Assert.assertTrue(topMenuTexts.contains("Домашняя страница"));
        Assert.assertTrue(topMenuTexts.contains("Моя страница"));
        Assert.assertTrue(topMenuTexts.contains("Проекты"));
        Assert.assertFalse(topMenuTexts.contains("Администрирование"));
        Assert.assertTrue(topMenuTexts.contains("Помощь"));
        Assert.assertTrue(topMenuTexts.contains("Моя учётная запись"));
        Assert.assertTrue(topMenuTexts.contains("Выйти"));
        Assert.assertFalse(topMenuTexts.contains("Войти"));
        Assert.assertFalse(topMenuTexts.contains("Регистрация"));
        WebElement search = webDriver.findElement(By.xpath("//label[@for='q']"));
        Assert.assertEquals(search.getText(), "Поиск:");
    }

    private static User generateAcceptedUser() {
        return new User().create();
    }

    private static void deleteUser(User user) {
        user.delete();
    }
}
