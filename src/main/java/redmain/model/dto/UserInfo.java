package redmain.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Accessors(chain = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfo {
    Integer id;
    String login;
    Boolean admin;
    String firstname;
    String lastname;
    LocalDateTime createdOn;
    LocalDateTime lastLoginOn;
    String apiKey;
    Integer status;
    String mail;
    String password;


}
