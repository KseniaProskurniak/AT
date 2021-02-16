package redmine.model.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import redmine.db.request.UserRequests;
import redmine.model.Generatable;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)

public class User implements Generatable<User> {
    private String login = "admin";
    private String password = "admin123";
    private Boolean admin = false;
    private Integer status = 1;
    // generateRandomString(40, "0123456789abcdef");

    public String getApiKey() {
        //TODO Изменить на генерацию ключа API
        return "f02b2da01a468c4116be898911481d1b928c15f9";
    }

    @Override
    public User read() {
        return null;
    }

    @Override
    public User update() {
        return null;
    }

    @Override
    public User create() {
        return UserRequests.createUser(this);
    }

}
