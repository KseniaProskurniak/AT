package redmine.model.user.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Accessors(fluent = true)
@Getter
@AllArgsConstructor
public enum MailNotification {
    ALL("О всех событиях во всех моих проектах"),
    ONLY_MY_EVENTS("Только для объектов, которые я отслеживаю или в которых участвую"),
    ONLY_ASSIGNED("Только для объектов, которые я отслеживаю или которые мне назначены"),
    ONLY_OWNER("Только для объектов, которые я отслеживаю или для которых я владелец"),
    NONE("Нет событий");

    String description;

}
