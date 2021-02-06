package redmine.model.member;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import redmine.model.project.Project;
import redmine.model.user.User;

import java.time.Instant;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Member {
    Integer id;
    User user;
    Project project;
    Instant createdOn;
    Boolean mailNotification;
}
