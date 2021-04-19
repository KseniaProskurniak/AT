package redmine.ui.pages;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class PageConstant {
    public static final Map<String, String> PAGE_URLS = ImmutableMap.<String, String>builder()
            .put("Домашняя страница", "http://edu-at.dfu.i-teco.ru/")
            .put("Страница авторизации", "http://edu-at.dfu.i-teco.ru/login")
            .put("Моя страница", "http://edu-at.dfu.i-teco.ru/my/page")
            .put("Администрирование", "http://edu-at.dfu.i-teco.ru/admin")
            .put("Пользователи", "http://edu-at.dfu.i-teco.ru/users")
            .put("Проекты", "http://edu-at.dfu.i-teco.ru/projects")
            .build();
}
