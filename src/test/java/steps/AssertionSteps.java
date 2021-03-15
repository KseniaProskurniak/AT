package steps;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.То;
import org.testng.Assert;
import redmine.managers.Context;
import redmine.ui.pages.helpers.CucumberPageObjectHelper;
import redmine.utils.BrowserUtils;
import redmine.utils.StringGenerators;

public class AssertionSteps {

    @И("значение переменной {string} равно {int}")
    public void assertResult(String stashId, Integer expectedResult) {
        Integer result = Context.get(stashId, Integer.class);
        Assert.assertEquals(result, expectedResult);
    }


    @То("на главной странице отображается поле {string}")
    public void assertProjectElementIsDisplayed(String fieldName) {
        Assert.assertTrue(
                BrowserUtils.isElementCurrentlyPresent(CucumberPageObjectHelper.getElementBy("Заголовок", fieldName))
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

    @То("нажать кнопку {string} в меню {string}")
    public void clickButton(String fieldName, String pageName) {
        CucumberPageObjectHelper.getElementBy(pageName, fieldName).click();
    }

    @То("ввести в поле {string} случайное значение")
    public void fullField(String fieldName) {
        if(fieldName.equals("Email")){
            CucumberPageObjectHelper.getElementBy("Новый пользователь", fieldName).sendKeys(StringGenerators.randomEmail());
        } else {
            CucumberPageObjectHelper.getElementBy("Новый пользователь", fieldName).sendKeys(StringGenerators.randomEnglishLowerString(8));
        }

//        switch (fieldName){
//            case "Пользователь":{
//                CucumberPageObjectHelper.getElementBy("Новый пользователь", fieldName).sendKeys(user.getLogin());
//            }
//            case "Имя": {
//                CucumberPageObjectHelper.getElementBy("Новый пользователь", fieldName).sendKeys(user.getFirstname());
//            }
//            case "Фамилия": {
//                CucumberPageObjectHelper.getElementBy("Новый пользователь", fieldName).sendKeys(user.getLastname());
//            }
        }
    }


