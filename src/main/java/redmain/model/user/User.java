package redmain.model.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import redmain.model.user.enums.Language;
import redmain.model.user.enums.MailNotification;
import redmain.model.user.enums.Status;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Integer id;
    String login;
    String hashedPassword;
    String firstname;
    String lastname;
    Boolean admin;
    Status status = Status.NOT_ACTIVE;
    LocalDateTime lastLoginOn;
    Language language = Language.EN;
    Integer authSourceId;
    LocalDateTime createdOn;
    LocalDateTime updatedOn;
    String type = getClass().getSimpleName();
    String identityUrl;
    MailNotification mailNotification = MailNotification.ALL;
    String salt;
    Boolean mustChangePasswd;
    LocalDateTime passwdChangedOn;


}
