package redmine.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import redmine.managers.Manager;
import redmine.ui.pages.helpers.CucumberName;

/**
 * Страница входа
 */

@CucumberName("Вход в систему")
public class LoginPage extends AbstractPage {

    @CucumberName("Логин")
    @FindBy(xpath = "//input[@id='username']")
    private WebElement loginElement;

    @CucumberName("Пароль")
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordElement;

    @CucumberName("Войти")
    @FindBy(xpath = "//a[@class='login']")
    public WebElement submitButton;

    @CucumberName("Вход")
    @FindBy(xpath = "//input[@id='login-submit']")
    public WebElement loginSubmit;

    @CucumberName("Регистрация")
    @FindBy(xpath = "//a[@class='register']")
    public WebElement registration;

    @CucumberName("Ошибка")
    @FindBy(xpath = "//div[@id = 'flash_error']")
    public WebElement flashError;

    @Step("Вход в систему Redmine с логином {0} и паролем {1}")
    public void login(String login, String password) {
        loginElement.sendKeys(login);
        passwordElement.sendKeys(password);
        loginSubmit.click();
        Manager.takeScreenshot();
    }

    public String errorMessage() {
        return flashError.getText();
    }
}