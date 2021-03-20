package ui_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import redmine.model.project.Project;
import redmine.model.role.Role;
import redmine.model.user.User;
import redmine.ui.pages.LoginPage2;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectVisibilityByUserTest {
    private WebDriver webDriver;
    private User user;
    private Project publicProject;
    private Project privateProject;
    private Project privateProjectWithMember;

    @BeforeClass
    void init() {
        user = new User().create();
        publicProject = Project.generate().create();
        privateProject = Project.generate().setIsPublic(false).create();
        privateProjectWithMember = Project.generate().setIsPublic(false).create()
                .addMember(user, Collections.singletonList(new Role().setId(3)));
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @AfterClass
    void quite() {
        //webDriver.quit();
        user.delete();
        publicProject.delete();
        privateProject.delete();
        privateProjectWithMember.delete();
    }

    @Test
    void test(){
        System.out.println(privateProjectWithMember);
    }

    @Test
    void testPublicProject() {
        LoginPage2 loginPage = new LoginPage2(webDriver);
        loginPage.login(user.getLogin(), user.getPassword());
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/my/page");
        WebElement projectsMenu = webDriver.findElement(By.xpath("//a[@class='projects']"));
        projectsMenu.click();
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/projects");
        List<WebElement> projects = webDriver.findElements(By.xpath("//div[@id='projects-index']//a"));
        List<String> projectNames = projects.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        System.out.println(projectNames);
        Assert.assertTrue(projectNames.contains(publicProject.getName()));
        Assert.assertFalse(projectNames.contains(privateProject.getName()));
        Assert.assertTrue(projectNames.contains(privateProjectWithMember.getName()));
    }
}
