package redmine.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import redmine.ui.pages.helpers.CucumberName;

import java.util.List;

@CucumberName("Проекты")
public class ProjectsPage extends AbstractPage {

    @CucumberName("Проекты")
    @FindBy(xpath = "//div[@id='content']//*[text() = 'Проекты']")
    public WebElement project;

    @CucumberName("список проектов")
    @FindBy(xpath = "//div[@id='projects-index']//a")
    public List<WebElement> projectsIndex;

}
