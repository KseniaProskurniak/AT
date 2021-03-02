package redmine.db.request;

import redmine.managers.Manager;
import redmine.model.user.User;

import java.util.List;
import java.util.Map;

public class UserRequests {

    public static User createUser(User user){

        String query = "INSERT INTO public.users " +
                "(id, login, hashed_password, firstname, lastname, admin, status, language, type, " +
                "mail_notification, salt) " +
                "VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?) RETURNING id;";
        List<Map<String, Object>> result = Manager.dbConnection.executePreparedQuery(query,
                user.getLogin(),
                user.getPassword(),
                user.getFirstname(),
                user.getLastname(),
                user.getAdmin(),
                user.getStatus(),
                "ru",
                "User",
                "all",
                user.getSalt()
        );
        user.setId((Integer) result.get(0).get("id"));
        return user;


        //запрос в БД
        //установка id значением, сгенерированным БД

    }
}
