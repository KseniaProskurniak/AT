package redmain.model.role;

import java.util.stream.Stream;

public enum UsersVisibility {
    ALL("Все активные пользователи"),
    NUMBERS_OF_VISIBLE_PROJECTS("Участники видимых проектов");

    public final String description;

    UsersVisibility(String description) {
        this.description = description;
    }

    public static UsersVisibility of(String description) {
        return Stream.of(values())
                .filter(it -> description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найден UsersVisibility по описанию " + description));
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
