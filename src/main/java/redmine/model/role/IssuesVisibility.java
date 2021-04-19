package redmine.model.role;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor

public enum IssuesVisibility {
    ALL("Все задачи"),
    DEFAULT("Только общие задачи"),
    OWN("Задачи созданные и назначенные пользователю");

    public final String description;


    public static IssuesVisibility of(String description1) {
        return Stream.of(values())
                .filter(it -> description1.equals(it.description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найден IssuesVisibility по описанию " + description1));
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
