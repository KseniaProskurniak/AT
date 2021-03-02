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
public class LoginByNotAcceptedUserTest {

    private WebDriver webDriver;
    private User user;

    @BeforeClass
    void init() {
        user = generateNotAcceptedUser();
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
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/login");
        List<WebElement> topMenuElements = webDriver.findElements(By.xpath("//div[@id='top-menu']//li"));
        List<String> topMenuTexts = topMenuElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Assert.assertTrue(topMenuTexts.contains("Домашняя страница"));
        Assert.assertFalse(topMenuTexts.contains("Моя страница"));
        Assert.assertTrue(topMenuTexts.contains("Проекты"));
        Assert.assertFalse(topMenuTexts.contains("Администрирование"));
        Assert.assertTrue(topMenuTexts.contains("Помощь"));
        Assert.assertFalse(topMenuTexts.contains("Выйти"));
        Assert.assertTrue(topMenuTexts.contains("Войти"));
        Assert.assertTrue(topMenuTexts.contains("Регистрация"));
        WebElement search = webDriver.findElement(By.xpath("//label[@for='q']"));
        Assert.assertEquals(search.getText(), "Поиск:");
        WebElement errorMessage = webDriver.findElement(By.xpath("//div[@id='flash_error']"));
        Assert.assertEquals(errorMessage.getText(), "Ваша учётная запись создана и ожидает подтверждения администратора.");
    }

    private static User generateNotAcceptedUser() {
        return new User().setStatus(2).create();
    }

    private static void deleteUser(User user) {
       user.delete();
    }
}
