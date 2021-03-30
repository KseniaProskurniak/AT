package steps;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Пусть;
import redmine.db.request.ProjectRequests;
import redmine.db.request.UserRequests;
import redmine.managers.Context;
import redmine.model.project.Project;
import redmine.model.role.Role;
import redmine.model.user.User;

import java.util.Collections;
import java.util.Map;

public class GenerationSteps {
    @Пусть("существует пользователь {string} с параметрами:")
    public void generateAndSaveUse(String stashId, Map<String, String> params) {
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
        Context.put(stashId, user);

    }

    @И("существует проект со статусом публичности {string} c сохраненным по ключу {string} именем")
    public void generateAndSaveProject(String status, String key) {
        Project project = Project.generate().setIsPublic(Boolean.valueOf(status));
        ProjectRequests.create(project);
        Context.put(key, project.getName());
    }

    @И("у пользователя {string} есть доступ к проекту с именем сохраненным по ключу {string}")
    public void userAccessToTheProject(String userName, String projectKey) {
        Project project = ProjectRequests.getByName((String) Context.get(projectKey));
        User user = UserRequests.findByLogin(userName);
        project.addMember(user, Collections.singletonList(new Role().setId(3)));
    }
}
