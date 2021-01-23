package redmain.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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
