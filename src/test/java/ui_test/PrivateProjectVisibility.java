package ui_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redmine.db.request.ProjectRequests;
import redmine.model.project.Project;
import redmine.model.user.Admin;
import redmine.model.user.User;
import redmine.ui.pages.LoginPage;

import java.util.List;
import java.util.stream.Collectors;

public class PrivateProjectVisibility {

    private WebDriver webDriver;
    private User admin;

    @BeforeClass
    void init() {
        admin = new Admin().create();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @AfterClass
    void quite() {
        //webDriver.quit();
        deleteUser(admin);
    }


    @Test
    void testPrivateProjectByAdmin() {
        Project project = Project.generate().setIsPublic(false);
        ProjectRequests.create(project);
        System.out.println(project);
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(admin.getLogin(), admin.getPassword());
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/my/page");
        WebElement projectsMenu = webDriver.findElement(By.xpath("//a[@class='projects']"));
        projectsMenu.click();
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/projects");
        List<WebElement> projects = webDriver.findElements(By.xpath("//div[@id='projects-index']//a"));
        List<String> projectNames = projects.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        System.out.println(projectNames);
        Assert.assertTrue(projectNames.contains(project.getName()));
    }

    private static void deleteUser(User user) {
        user.delete();
    }
}
