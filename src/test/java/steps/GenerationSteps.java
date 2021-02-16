package steps;

import cucumber.api.java.ru.Пусть;
import redmine.managers.Context;
import redmine.model.user.User;

import java.util.Map;

public class GenerationSteps {
    @Пусть("существует пользователь {string} с параметрами:")
    public void generateAndSaveUse(String stashId, Map<String,String> params) {
        User user = new User();

        if (params.containsKey("login")){
            user.setLogin(params.get("login"));
        }

        if (params.containsKey("password")){
            user.setPassword(params.get("password"));
        }

        if (params.containsKey("admin")){
            user.setAdmin(Boolean.parseBoolean(params.get("admin")));
        }
        if (params.containsKey("status")){
            user.setStatus(Integer.parseInt(params.get("status")));
        }
                user.generate();
        Context.put(stashId, user);

    }
}
