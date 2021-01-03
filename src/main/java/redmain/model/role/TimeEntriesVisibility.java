package redmain.model.role;

import java.util.stream.Stream;

public enum TimeEntriesVisibility {
    ALL("Все трудозатраты"),
    OWN("Собственные трудозатраты");

    public final String description;

    TimeEntriesVisibility(String description) {
        this.description = description;
    }

    public static TimeEntriesVisibility of(String description) {
        return Stream.of(values())
                .filter(it -> description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найден TimeEntriesVisibility по описанию " + description));
    }
}
