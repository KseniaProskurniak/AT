package redmine.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import redmine.ui.pages.helpers.CucumberName;

@CucumberName("Проекты")
public class ProjectPage {

    @CucumberName("Проекты")
    @FindBy(xpath = "//div[@id='content']//*[text() = 'Проекты']")
    public WebElement project;

    @CucumberName("список проектов")
    @FindBy(xpath = "//div[@id='projects-index']")
    public WebElement projectsIndex;

}
