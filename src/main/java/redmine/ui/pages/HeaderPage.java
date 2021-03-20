package redmine.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import redmine.ui.pages.helpers.CucumberName;

import java.awt.*;
import java.util.List;

/**
 * Заголовок страницы
 */

@CucumberName("Домашняя страница")
public class HeaderPage extends AbstractPage {

    @CucumberName("Элементы заголовка")
    @FindBy(xpath = "//div[@id='top-menu']/ul//a")
    public List<WebElement> headerElements;

    @CucumberName("Строка поиска")
    @FindBy(xpath = "//div[@id='quick-search']/form/input[@type='text']")
    public WebElement searchInput;

    @CucumberName("Домашняя страница")
    @FindBy(xpath = "//a[@class='home']")
    private WebElement home;

    @CucumberName("Моя страница")
    @FindBy(xpath = "//a[@class='my-page']")
    private WebElement myPage;

    @CucumberName("Проекты")
    @FindBy(xpath = "//a[@class='projects']")
    public WebElement projects;

    @CucumberName("Администрирование")
    @FindBy(xpath = "//a[@class='administration']")
    public WebElement administration;

    @CucumberName("Помощь")
    @FindBy(xpath = "//a[@class='help']")
    public WebElement help;

    @CucumberName("Вошли как")
    @FindBy(xpath = "//div[@id='loggedas']")
    public WebElement loggedAs;

    @CucumberName("Моя учётная запись")
    @FindBy(xpath = "//a[@class='my-account']")
    public WebElement myAccount;

    @CucumberName("Выйти")
    @FindBy(xpath = "//a[@class='logout']")
    public WebElement logOut;

    @CucumberName("Найти кнопку")
    @FindBy(className = "b-form-button__input")
    private Button searchButton;



    public String loggedAs() {
        return loggedAs.getText();
    }


}
