package redmine.model.user;

import lombok.Data;
import lombok.experimental.Accessors;
import redmine.db.request.UserRequests;
import redmine.model.Generatable;
import redmine.utils.StringGenerators;

/**
 * Класс-модель пользователя в системе.
 */
@Data
@Accessors(chain = true)
public class User implements Generatable<User> {
    private Integer id;
    private String login = StringGenerators.randomEnglishLowerString(8);
    private String password = StringGenerators.randomEnglishString(8);
    private Boolean admin = false;
    private Integer status = 1;
    private String firstname = StringGenerators.randomEnglishString(8);
    private String lastname = StringGenerators.randomEnglishString(8);
    private String email = StringGenerators.randomEmail();
    private String salt = StringGenerators.randomString(32, "0123456789abcdef");
    private String apiKey = StringGenerators.randomString(40, "0123456789abcdef");


//    public String getApiKey() {
//        return "f02b2da01a468c4116be898911481d1b928c15f9";
//    }

    @Override
    public User read() {
        //todo реализовать
        return null;
    }

    @Override
    public User update() {
        //todo реализовать
        return null;
    }

    @Override
    public User create() {
        return UserRequests.createUser(this);
    }

    @Override
    public void delete() {
    UserRequests.deleteUser(this);
    }
}
