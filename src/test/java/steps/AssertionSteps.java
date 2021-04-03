package steps;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.То;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import redmine.managers.Context;
import redmine.managers.Manager;
import redmine.model.project.Project;
import redmine.model.user.User;
import redmine.ui.pages.HomePage;
import redmine.ui.pages.NewUserPage;
import redmine.ui.pages.PageConstant;
import redmine.ui.pages.helpers.CucumberPageObjectHelper;
import redmine.ui.pages.helpers.Pages;
import redmine.utils.BrowserUtils;
import redmine.utils.StringGenerators;

import java.util.List;
import java.util.stream.Collectors;

public class AssertionSteps {

    private static final String CURRENT_PAGE = "CURRENT_PAGE";

    WebDriver webDriver = Manager.driver();

    @И("значение переменной {string} равно {int}")
    public void assertResult(String stashId, Integer expectedResult) {
        Integer result = Context.get(stashId, Integer.class);
        Assert.assertEquals(result, expectedResult);
    }


    @То("на главной странице отображается поле {string}")
    public void fieldIsDisplayed(String fieldName) {
        Assert.assertTrue(
                BrowserUtils.isElementCurrentlyPresent(CucumberPageObjectHelper
                        .getElementBy("Домашняя страница", fieldName))
        );
    }

    @То("на странице {string} отображается элемент {string}")
    public void assertProjectElementIsDisplayed(String pageName, String elementName) {
        Assert.assertTrue(
                BrowserUtils.isElementCurrentlyPresent(CucumberPageObjectHelper.getElementBy(pageName, elementName))
        );
    }

    @То("на странице {string} не отображается элемент {string}")
    public void assertProjectElementNotDisplayed(String pageName, String elementName) {
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
                BrowserUtils.isElementCurrentlyPresent(CucumberPageObjectHelper
                        .getElementBy("Домашняя страница", fieldName))
        );
    }

    @То("нажать кнопку в верхнем меню {string}")
    public void clickButtonInUpper(String fieldName) {
        CucumberPageObjectHelper.getElementBy("Домашняя страница", fieldName).click();
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
            CucumberPageObjectHelper.getElementBy("Новый пользователь", fieldName)
                    .sendKeys(StringGenerators.randomEmail());
        } else {
            CucumberPageObjectHelper.getElementBy("Новый пользователь", fieldName)
                    .sendKeys(StringGenerators.randomEnglishLowerString(8));
        }
    }

    @То("заполнить и подтвердить пароль")
    public void confirmationPassword() {
        String pass = StringGenerators.randomString(8, StringGenerators.ENGLISH_LOWER);
        CucumberPageObjectHelper.getElementBy("Новый пользователь", "Пароль")
                .sendKeys(pass);
        CucumberPageObjectHelper.getElementBy("Новый пользователь", "Подтверждение")
                .sendKeys(pass);
    }

    @То("нажать чекбокс {string}")
    public void clickCheckBox(String checkBoxName) {
        CucumberPageObjectHelper.getElementBy("Новый пользователь", checkBoxName).click();
    }

    @И("проверяет корректность создания")
    public void checkResult() {
        String message = Pages.getPage(NewUserPage.class).message.getText();
        String login = Pages.getPage(NewUserPage.class).userLogin.getAttribute("value");
        Assert.assertEquals(message, String.format("Пользователь %s создан.", login));
    }

    @И("на странице {string} нажать на элемент {string}")
    public void assertFieldIsNotDisplayed(String pageName, String fieldName) {
        WebElement element = CucumberPageObjectHelper.getElementBy(pageName, fieldName);
        element.click();
    }

    @И("вошли как пользователь {string}")
    public void loggedInAs(String stashId) {
        String loggedAs = Pages.getPage(HomePage.class).loggedAs.getText();
        User user = Context.get(stashId, User.class);
        Assert.assertTrue(loggedAs.contains(user.getLogin()));
    }

    @И("открыта страница по пути {string}")
    public void checkPage(String path) {
        Assert.assertEquals(path, webDriver.getCurrentUrl(), "Отображена неверная страница");
    }

    @И("открыта страница {string}")
    public void checkPageByName(String pageName) {
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals(PageConstant.PAGE_URLS.get(pageName), currentUrl, "Отображена неверная страница");
        Context.put(CURRENT_PAGE, pageName);
    }

    @И("на странице {string} отображается {string} с ранее сохраненным именем проекта по ключу {string}")
    public void checkNameProject(String pageName, String fieldName, String projectStashId) {
        List<WebElement> elements = CucumberPageObjectHelper.getElementsBy(pageName, fieldName);
        Project project = Context.get(projectStashId, Project.class);
        Assert.assertTrue(elements.stream()
                .map(element -> element.getText())
                .anyMatch(name -> name.equals(project.getName())));
    }

    @И("на странице {string} не отображается {string} с ранее сохраненным именем проекта по ключу {string}")
    public void checkAbsentNameProject(String pageName, String fieldName, String projectStashId) {
        List<WebElement> elements = CucumberPageObjectHelper.getElementsBy(pageName, fieldName);
        Project project = Context.get(projectStashId, Project.class);
        Assert.assertTrue(elements.stream()
                .map(element -> element.getText())
                .noneMatch(name -> name.equals(project.getName())));
    }


    @И("таблица с пользователями не отсортирована по полю {string}")
    public void checkUnsorted(String fieldName) {
        String pageName = (String) Context.get(CURRENT_PAGE);
        List<WebElement> elements = CucumberPageObjectHelper.getElementsBy(pageName, fieldName);
        List<String> names = elements.stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
        List<String> sortedNames = names.stream()
                .sorted((n1, n2) -> n1.compareToIgnoreCase(n2))
                .collect(Collectors.toList());
        Assert.assertNotEquals(sortedNames, names);
    }


    @И("таблица с пользователями отсортирована по полю {string} по возрастанию")
    public void checkSorted(String fieldName) {
        String pageName = (String) Context.get(CURRENT_PAGE);
        List<WebElement> elements = CucumberPageObjectHelper.getElementsBy(pageName, fieldName);
        List<String> names = elements.stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
        List<String> sortedNames = names.stream()
                .sorted((n1, n2) -> n1.compareToIgnoreCase(n2))
                .collect(Collectors.toList());
        Assert.assertEquals(sortedNames, names);
    }

    @И("таблица с пользователями отсортирована по полю {string} по убыванию")
    public void checkDescSorted(String fieldName) {
        String pageName = (String) Context.get(CURRENT_PAGE);
        List<WebElement> elements = CucumberPageObjectHelper.getElementsBy(pageName, fieldName);
        List<String> names = elements.stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
        List<String> sortedNames = names.stream()
                .sorted((n1, n2) -> n2.compareToIgnoreCase(n1))
                .collect(Collectors.toList());
        Assert.assertEquals(sortedNames, names);
    }


    @И("в шапке таблицы нажать на заголовок {string}")
    public void clickTableColumn(String columnName) {
        String pageName = (String) Context.get(CURRENT_PAGE);
        WebElement element = CucumberPageObjectHelper.getElementBy(pageName, columnName);
        element.click();
    }
}


