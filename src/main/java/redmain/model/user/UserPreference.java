package redmain.model.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPreference {
    Integer id;
    User user;
    String others;
    Boolean hideMail;
    String timeZone;

}
