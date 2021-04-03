package redmine.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import redmine.ui.pages.helpers.CucumberName;

import java.util.List;

@CucumberName("Пользователи")
public class UsersPage extends AbstractPage {

    // Заголовок таблицы
    @CucumberName("Пользователь")
    @FindBy(xpath = "//a[text()='Пользователь']")
    public WebElement username;

    @CucumberName("Имя")
    @FindBy(xpath = "//a[text()='Имя']")
    public WebElement firstname;

    @CucumberName("Фамилия")
    @FindBy(xpath = "//a[text()='Фамилия']")
    public WebElement lastname;

    // Элементы таблицы
    @CucumberName("Пользователь")
    @FindBy(xpath = "//td[@class='username']")
    public List<WebElement> usernames;

    @CucumberName("Имя")
    @FindBy(xpath = "//td[@class='firstname']")
    public List<WebElement> firstnames;

    @CucumberName("Фамилия")
    @FindBy(xpath = "//td[@class='lastname']")
    public List<WebElement> lastnames;

    @CucumberName("Таблица пользователей")
    @FindBy(xpath = "//table[contains(@class, 'users')]")
    public WebElement userTable;

}
