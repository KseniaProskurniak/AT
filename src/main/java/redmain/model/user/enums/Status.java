package redmain.model.user.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Accessors(fluent = true)
@Getter
@AllArgsConstructor
public enum Status {
    NOT_ACTIVE("Неактивный"),
    ACTIVE("Активный"),
    BLOCKED("Заблокированный");

    String description;

}
