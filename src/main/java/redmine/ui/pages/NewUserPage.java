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

    @CucumberName("Создать")
    @FindBy(xpath = "//*[@name='commit']")
    public WebElement commit;


}
