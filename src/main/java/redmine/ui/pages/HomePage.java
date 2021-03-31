package redmine.ui.pages;

import org.openqa.selenium.support.FindBy;
import redmine.ui.pages.helpers.CucumberName;

import java.awt.*;

/**
 * Заголовок страницы
 */

@CucumberName("Домашняя страница")
public class HomePage extends AbstractPage {

    @CucumberName("Найти кнопку")
    @FindBy(className = "b-form-button__input")
    private Button searchButton;


}
