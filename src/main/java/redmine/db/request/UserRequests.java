package redmine.db.request;

import redmine.model.user.User;

public class UserRequests {

    public static User createUser(User user){
        //запрос в БД
        //установка id значением, сгенерированным БД
        return user;
    }
}
