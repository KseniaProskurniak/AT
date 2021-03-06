package redmine.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import redmine.ui.pages.helpers.CucumberName;

import java.util.List;

/**
 * Страница "Пользователи" в меню "Администрирование"
 */

@CucumberName("Администрирование пользователей")
public class AdministrationUsersPage extends AbstractPage {

    @FindBy(xpath = "//table[@class='list users']//a[text()='Создано']")
    public WebElement sortByCreatedOn;

    @FindBy(xpath = "//tr[@class='user active']/td[@class='created_on']")
    public List<WebElement> createdOn;

}
