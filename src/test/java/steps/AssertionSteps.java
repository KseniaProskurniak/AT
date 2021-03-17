package steps;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.То;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import redmine.managers.Context;
import redmine.ui.pages.HeaderPage;
import redmine.ui.pages.NewUserPage;
import redmine.ui.pages.helpers.CucumberPageObjectHelper;
import redmine.ui.pages.helpers.Pages;
import redmine.utils.BrowserUtils;
import redmine.utils.StringGenerators;

public class AssertionSteps {


    @И("значение переменной {string} равно {int}")
    public void assertResult(String stashId, Integer expectedResult) {
        Integer result = Context.get(stashId, Integer.class);
        Assert.assertEquals(result, expectedResult);
    }


    @То("на главной странице отображается поле {string}")
    public void fieldIsDisplayed(String fieldName) {
        Assert.assertTrue(
                BrowserUtils.isElementCurrentlyPresent(CucumberPageObjectHelper.getElementBy("Заголовок", fieldName))
        );
    }

    @То("на странице {string} отображается элемент {string}")
    public void assertProjectElementIsDisplayed(String pageName,String elementName) {
        Assert.assertTrue(
                BrowserUtils.isElementCurrentlyPresent(CucumberPageObjectHelper.getElementBy(pageName, elementName))
        );
    }

    @То("на странице {string} не отображается элемент {string}")
    public void assertProjectElementNotDisplayed(String pageName,String elementName) {
        Assert.assertFalse(
                BrowserUtils.isElementCurrentlyPresent(CucumberPageObjectHelper.getElementBy(pageName, elementName))
        );
    }

    @То("на странице {string} отображается поле {string}")
    public void assertProjectFieldIsDisplayed(String pageName, String fieldName) {
        Assert.assertTrue(
                BrowserUtils.isElementCurrentlyPresent(CucumberPageObjectHelper.getElementBy(pageName, fieldName))
        );
    }


    @То("на главной странице не отображается поле {string}")
    public void assertProjectElementIsNotDisplayed(String fieldName) {
        Assert.assertFalse(
                BrowserUtils.isElementCurrentlyPresent(CucumberPageObjectHelper.getElementBy("Заголовок", fieldName))
        );
    }


    @То("нажать кнопку в верхнем меню {string}")
    public void clickButtonInUpper(String fieldName) {
        CucumberPageObjectHelper.getElementBy("Заголовок", fieldName).click();
    }

    @То("нажать кнопку {string}")
    public void clickButton(String fieldName) {
        CucumberPageObjectHelper.getElementBy("Администрирование пользователей", fieldName).click();
    }


    @То("на странице {string} нажать кнопку {string}")
    public void clickButton(String pageName, String fieldName) {
        CucumberPageObjectHelper.getElementBy(pageName, fieldName).click();
    }

    @То("ввести в поле {string} случайное значение")
    public void fullField(String fieldName) {
        if (fieldName.equals("Email")) {
            CucumberPageObjectHelper.getElementBy("Новый пользователь", fieldName).sendKeys(StringGenerators.randomEmail());
        } else {
            CucumberPageObjectHelper.getElementBy("Новый пользователь", fieldName).sendKeys(StringGenerators.randomEnglishLowerString(8));
        }
    }

    @То("заполнить и подтвердить пароль")
    public void confirmationPassword() {
        String pass = StringGenerators.randomString(8, StringGenerators.ENGLISH_LOWER);
        CucumberPageObjectHelper.getElementBy("Новый пользователь", "Пароль").sendKeys(pass);
        CucumberPageObjectHelper.getElementBy("Новый пользователь", "Подтверждение").sendKeys(pass);
    }


    @То("нажать чекбокс {string}")
    public void clickСheckBox(String checkBoxName) {
        CucumberPageObjectHelper.getElementBy("Новый пользователь", checkBoxName).click();
    }


    @И("проверяет корректность создания")
    public void checkResult() {
        String message = Pages.getPage(NewUserPage.class).message.getText();
        String login = Pages.getPage(NewUserPage.class).userLogin.getAttribute("value");
        message.contains(login);
    }


    @И("на странице {string} нажать на элемент {string}")
    public void assertFieldIsNotDisplayed(String pageName, String fieldName) {
        WebElement element = CucumberPageObjectHelper.getElementBy(pageName, fieldName);
        element.click();
    }

    @И("вошли как пользователь из предыстории")
    public void loggedInAs() {
        String loggedAs = Pages.getPage(HeaderPage.class).loggedAs.getText();
        String login = Pages.getPage(HeaderPage.class).loggedAs.getText();
        loggedAs.contains(login);
    }
}


