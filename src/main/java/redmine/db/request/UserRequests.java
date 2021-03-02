package redmine.db.request;

import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;
import redmine.managers.Manager;
import redmine.model.user.User;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

public class UserRequests {
    @SneakyThrows
    public static User createUser(User user) {
        String query = "INSERT INTO public.users " +
                "(id, login, hashed_password, firstname, lastname, admin, status, language, type, " +
                "mail_notification, salt) " +
                "VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?) RETURNING id;";
        List<Map<String, Object>> result = Manager.dbConnection.executePreparedQuery(query,
                user.getLogin(),
                hashPassword(user.getSalt(), user.getPassword()),
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

        query = "INSERT INTO public.tokens " +
                "(id, user_id, action, value, created_on) " +
                "VALUES(DEFAULT,?,'api',?,?) RETURNING id;";
        PreparedStatement prepared = Manager.dbConnection.getConnection().prepareStatement(query);
        prepared.setInt(1, user.getId());
        prepared.setString(2, user.getApiKey());
        prepared.setDate(3, new java.sql.Date(System.currentTimeMillis()));
        prepared.executeQuery();

        return user;

    }

    private static String hashPassword(String salt, String plainPassword) {
        return DigestUtils.sha1Hex(salt + DigestUtils.sha1Hex(plainPassword));
    }
}
