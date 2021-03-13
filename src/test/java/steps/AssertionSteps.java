package steps;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.То;
import org.testng.Assert;
import redmine.managers.Context;
import redmine.ui.pages.helpers.CucumberPageObjectHelper;
import redmine.utils.BrowserUtils;

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


    @То("нажать кнопку {string}")
    public void clickButton(String fieldName) {
        CucumberPageObjectHelper.getElementBy("Заголовок", fieldName).click();
    }

}

