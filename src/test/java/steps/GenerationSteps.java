package steps;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Пусть;
import redmine.db.request.ProjectRequests;
import redmine.managers.Context;
import redmine.model.project.Project;
import redmine.model.role.Role;
import redmine.model.user.User;

import java.util.Collections;
import java.util.Map;

public class GenerationSteps {
    @Пусть("существует пользователь {string} с параметрами:")
    public void generateAndSaveUse(String userStashId, Map<String, String> params) {
        User user = new User();

        if (params.containsKey("login")) {
            user.setLogin(params.get("login"));
        }

        if (params.containsKey("password")) {
            user.setPassword(params.get("password"));
        }

        if (params.containsKey("admin")) {
            user.setAdmin(Boolean.parseBoolean(params.get("admin")));
        }
        if (params.containsKey("status")) {
            user.setStatus(Integer.parseInt(params.get("status")));
        }
        user.generate();
        Context.put(userStashId, user);

    }

    @И("существует проект со статусом публичности {string} с именем сохраненным по ключу {string}")
    public void generateAndSaveProject(String status, String projectStashId) {
        Project project = Project.generate().setIsPublic(Boolean.valueOf(status));
        ProjectRequests.create(project);
        Context.put(projectStashId, project);
    }

    @И("у пользователя {string} есть доступ к проекту с именем сохраненным по ключу {string}")
    public void userAccessToTheProject(String userStashId, String projectStashId) {
        Project project = (Project) Context.get(projectStashId);
        User user = (User) Context.get(userStashId);
        project.addMember(user, Collections.singletonList(new Role().setId(3)));
    }
}
