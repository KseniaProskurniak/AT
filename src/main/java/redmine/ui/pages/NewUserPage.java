package redmine.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import redmine.ui.pages.helpers.CucumberName;

@CucumberName("Новый пользователь")
public class NewUserPage extends AbstractPage {

    @CucumberName("Пользователь")
    @FindBy(xpath = "//*[@id = 'user_login']")
    public WebElement userLogin;

    @CucumberName("Имя")
    @FindBy(xpath = "//*[@id = 'user_firstname']")
    public WebElement userFirstname;

    @CucumberName("Фамилия")
    @FindBy(xpath = "//*[@id = 'user_lastname']")
    public WebElement userLastname;

    @CucumberName("Email")
    @FindBy(xpath = "//*[@id = 'user_mail']")
    public WebElement userMail;

    @CucumberName("Пароль")
    @FindBy(xpath = "//*[@id = 'user_password']")
    public WebElement userPassword;

    @CucumberName("Подтверждение")
    @FindBy(xpath = "//*[@id = 'user_password_confirmation']")
    public WebElement userPasswordConfirmation;

    @CucumberName("Создание пароля")
    @FindBy(xpath = "//*[@id = 'user_generate_password']")
    public WebElement userGeneratePassword;

    @CucumberName("Создать")
    @FindBy(xpath = "//*[@name='commit']")
    public WebElement commit;

    @CucumberName("Сообщение")
    @FindBy(xpath = "//*[@id='flash_notice']")
    public WebElement message;

}
