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
import redmine.ui.pages.LoginPage;

import java.util.List;
import java.util.stream.Collectors;

public class SortByNameAndFamilyByAdminTest {

    private WebDriver webDriver;
    private User admin;

    @BeforeClass
    void init(){
        admin = new Admin().create();
        webDriver = new ChromeDriver();
    }

    @AfterClass
    void drop() {
        //webDriver.quit();
        admin.delete();
    }

    @Test
    void testSortByName(){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(admin.getLogin(), admin.getPassword());
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/my/page");
        WebElement administrationMenu = webDriver.findElement(By.xpath("//a[@class='administration']"));
        administrationMenu.click();
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/admin");
        WebElement usersMenu = webDriver.findElement(By.xpath("//a[contains(@class, 'users')]"));
        usersMenu.click();
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/users");
        List<WebElement> userFirstNameElements = webDriver.findElements(By.xpath("//td[@class='firstname']"));
        List<String> userFirstNames = userFirstNameElements.stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
        System.out.println(userFirstNameElements);
    }


}
