package ui_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redmine.model.user.Admin;
import redmine.model.user.User;
import redmine.model.user.enums.Sort;
import redmine.ui.pages.LoginPage;

import java.util.List;
import java.util.stream.Collectors;

public class SortByUsernameTest {
    private WebDriver webDriver;
    private User admin;

    @BeforeClass
    void init() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        admin = new Admin().create();
        webDriver = new ChromeDriver();
    }

    @AfterClass
    void drop() {
        webDriver.quit();
        admin.delete();
    }

    @Test
    void testUsersCategory() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(admin.getLogin(), admin.getPassword());
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/my/page");
        WebElement administrationMenu = webDriver.findElement(By.xpath("//a[@class='administration']"));
        administrationMenu.click();
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/admin");
        WebElement usersMenu = webDriver.findElement(By.xpath("//a[contains(@class, 'users')]"));
        usersMenu.click();
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/users");
    }

    @Test(dependsOnMethods = "testUsersCategory")
    void testSortByUsername() {
        List<WebElement> usernameElements = webDriver.findElements(By.xpath("//td[@class='username']"));
        List<String> usernames = namesFrom(usernameElements);
        List<String> sortedUsernames = sortNamesFrom(usernames, Sort.ASC);
        Assert.assertEquals(sortedUsernames, usernames);
    }

    @Test(dependsOnMethods = "testSortByUsername")
    void testDescSortByUsername() {
        WebElement usernameHeaderElement = webDriver.findElement(By.xpath("//a[text()='Пользователь']"));
        usernameHeaderElement.click();
        List<WebElement> usernameElements = webDriver.findElements(By.xpath("//td[@class='username']"));
        List<String> usernames = namesFrom(usernameElements);
        List<String> sortedUsernames = sortNamesFrom(usernames, Sort.DESC);
        Assert.assertEquals(sortedUsernames, usernames);
    }

    private List<String> sortNamesFrom(List<String> names, Sort sort) {
        return names.stream()
                .sorted((n1, n2) -> n1.compareToIgnoreCase(n2) * (sort == Sort.ASC ? 1 : -1))
                .collect(Collectors.toList());
    }

    private List<String> namesFrom(List<WebElement> webElements) {
        return webElements.stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }
}
