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
    void testUsersCategory(){
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
    void testUnsortedByFirstName(){
        List<WebElement> userFirstNameElements = webDriver.findElements(By.xpath("//td[@class='firstname']"));
        List<String> userFirstNames = userFirstNameElements.stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
        List<String> sortedUserFirstNames = userFirstNames.stream().sorted().collect(Collectors.toList());
        Assert.assertNotEquals(sortedUserFirstNames, userFirstNames);
        System.out.println(userFirstNames);
        System.out.println(sortedUserFirstNames);
    }


    @Test(dependsOnMethods = "testUsersCategory")
    void testUnsortedByLastName(){
        List<WebElement> userLastNameElements = webDriver.findElements(By.xpath("//td[@class='lastname']"));
        List<String> userLastNames = userLastNameElements.stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
        List<String> sortedUserLastNames = userLastNames.stream().sorted().collect(Collectors.toList());
        Assert.assertNotEquals(sortedUserLastNames, userLastNames);
        System.out.println(userLastNames);
        System.out.println(sortedUserLastNames);
    }

    @Test(dependsOnMethods = "testDescSortByLastName")
    void testSortByFirstName(){
        WebElement userFirstNameHeaderElement = webDriver.findElement(By.xpath("//a[text()='Имя']"));
        userFirstNameHeaderElement.click();
        List<WebElement> userFirstNameElements = webDriver.findElements(By.xpath("//td[@class='firstname']"));
        List<String> userFirstNames = userFirstNameElements.stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
        List<String> sortedUserFirstNames = userFirstNames.stream()
                .sorted((n1,n2) -> n1.compareToIgnoreCase(n2))
                .collect(Collectors.toList());
        System.out.println(userFirstNames);
        System.out.println(sortedUserFirstNames);
        Assert.assertEquals(sortedUserFirstNames, userFirstNames);
    }

    @Test(dependsOnMethods = "testSortByFirstName")
    void testDescSortByFirstName(){
        WebElement userFirstNameHeaderElement = webDriver.findElement(By.xpath("//a[text()='Имя']"));
        userFirstNameHeaderElement.click();
        List<WebElement> userFirstNameElements = webDriver.findElements(By.xpath("//td[@class='firstname']"));
        List<String> userFirstNames = userFirstNameElements.stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
        List<String> sortedUserFirstNames = userFirstNames.stream()
                .sorted((n1,n2) -> n1.compareToIgnoreCase(n2)*(-1))
                //.sorted((n1,n2) -> n2.compareToIgnoreCase(n1))
                .collect(Collectors.toList());
        System.out.println(userFirstNames);
        System.out.println(sortedUserFirstNames);
        Assert.assertEquals(sortedUserFirstNames, userFirstNames);
    }

    @Test(dependsOnMethods = "testUnsortedByLastName")
    void testSortByLastName(){
        WebElement userLastNameHeaderElement = webDriver.findElement(By.xpath("//a[text()='Фамилия']"));
        userLastNameHeaderElement.click();
        List<WebElement> userLastNameElements = webDriver.findElements(By.xpath("//td[@class='lastname']"));
        List<String> userLastNames = userLastNameElements.stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
        List<String> sortedUserLastNames = userLastNames.stream()
                .sorted((n1,n2) -> n1.compareToIgnoreCase(n2))
                .collect(Collectors.toList());
        System.out.println(userLastNames);
        System.out.println(sortedUserLastNames);
        Assert.assertEquals(sortedUserLastNames, userLastNames);

    }

    @Test(dependsOnMethods = "testSortByLastName")
    void testDescSortByLastName(){
        WebElement userLastNameHeaderElement = webDriver.findElement(By.xpath("//a[text()='Фамилия']"));
        userLastNameHeaderElement.click();
        List<WebElement> userLastNameElements = webDriver.findElements(By.xpath("//td[@class='lastname']"));
        List<String> userLastNames = userLastNameElements.stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
        List<String> sortedUserLastNames = userLastNames.stream()
                .sorted((n1,n2) -> n2.compareToIgnoreCase(n1))
                .collect(Collectors.toList());
        System.out.println(userLastNames);
        System.out.println(sortedUserLastNames);
        Assert.assertEquals(sortedUserLastNames, userLastNames);
    }

}
