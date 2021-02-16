package steps;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Пусть;
import redmine.managers.Context;
import redmine.managers.Manager;
import redmine.model.user.User;
import redmine.ui.pages.LoginPage;

import static redmine.ui.pages.helpers.Pages.getPage;

public class LoginSteps {


    @И("открыт браузер на главной странице")
    public void openBrowser(){
        Manager.openPage("login");
    }

    @Пусть("авторизоваться пользователем {string}")
    public void authorizeBy(String userStashId) {
        User user = Context.getStash().get(userStashId, User.class);
        getPage(LoginPage.class).login(
                user.getLogin(), user.getPassword()
        );
    }
}
