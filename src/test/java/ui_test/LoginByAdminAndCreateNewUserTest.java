package ui_test;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import redmine.model.user.Admin;
import redmine.model.user.User;

@Slf4j
public class LoginByAdminAndCreateNewUserTest {

    private WebDriver webDriver;
    private User admin;
    private WebDriverWait wait;

    @BeforeClass
    void init() {
        admin = generateAdmin();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, 20);
    }

    @AfterClass
    void quite() {
        //webDriver.quit();
        deleteUser(admin);
    }

//    @Test
//    void createNewUser() {
//        User user = new User();
//        LoginPage loginPage = new LoginPage(webDriver);
//        loginPage.login(admin.getLogin(), admin.getPassword());
//        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/my/page");
//        WebElement loginAs = webDriver.findElement(By.xpath("//div[@id='loggedas']"));
//        WebElement administration = webDriver.findElement(By.xpath("//a[@class='administration']"));
//        administration.click();
//        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/admin");
//        // WebElement users = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@class, 'users')]")));
//        WebElement users = webDriver.findElement(By.xpath("//a[contains(@class, 'users')]"));
//        users.click();
//        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/users");
//        WebElement newUser = webDriver.findElement(By.xpath("//a[contains(@class, 'icon-add')]"));
//        newUser.click();
//        Assert.assertEquals(webDriver.getCurrentUrl(), "http://edu-at.dfu.i-teco.ru/users/new");
//        WebElement userLogin = webDriver.findElement(By.xpath("//input[@id='user_login']"));
//        userLogin.sendKeys(user.getLogin());
//        WebElement userFirstName = webDriver.findElement(By.xpath("//input[@id='user_firstname']"));
//        userFirstName.sendKeys(user.getFirstname());
//        WebElement userLastName = webDriver.findElement(By.xpath("//input[@id='user_lastname']"));
//        userLastName.sendKeys(user.getLastname());
//        WebElement userMail = webDriver.findElement(By.xpath("//input[@id='user_mail']"));
//        userMail.sendKeys(user.getEmail());
//        WebElement userGeneratePassword = webDriver.findElement(By.xpath("//input[@id='user_generate_password']"));
//        userGeneratePassword.click();
//        WebElement submit = webDriver.findElement(By.xpath("//input[@name='commit']"));
//        submit.click();
//        WebElement notice = webDriver.findElement(By.xpath("//div[@id='flash_notice']"));
//        Assert.assertEquals(notice.getText(), "Пользователь " + user.getLogin() + " создан.");
////Проверка в БД
//        User userFromDb = UserRequests.findByLogin(user.getLogin());
//        Assert.assertEquals(user.getLogin(), userFromDb.getLogin());
//
//    }


    private static User generateAdmin() {
        return new Admin().create();
    }

    private static void deleteUser(User user) {
        user.delete();
    }
}
