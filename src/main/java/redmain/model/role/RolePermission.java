package redmain.model.role;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum RolePermission {

    ADD_PROJECT("Создание проекта"),
    EDIT_PROJECT("Редактрование проектов"),
    CLOSE_PROJECT("Закрывать/открывать проекты"),
    SELECT_PROJECT_MODULES("Выбор модулей проекта"),
    MANAGE_MEMBERS("Управление участниками"),
    MANAGE_VERSIONS("Управление версиями"),
    ADD_SUBPROJECTS("Создание подпроектов"),
    MANAGE_PUBLIC_QUERIES("Управление общими запросами"),
    SAVE_QUERIES("Сохранение запросов");

    public final String description;


    public static RolePermission of(String description) {
        return Stream.of(values())
                .filter(it -> description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найден RolePermission по описанию " + description));
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
