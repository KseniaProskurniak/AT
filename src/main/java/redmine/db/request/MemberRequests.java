package redmine.db.request;

import redmine.managers.Manager;
import redmine.model.user.Member;
import redmine.model.user.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemberRequests {
    public static List<Member> getByProject(Integer id) {

        String query = "SELECT * FROM members m " +
                "INNER JOIN users u ON u.id = m.user_id WHERE m.project_id = ?";


        List<Map<String, Object>> results = Manager.dbConnection.executePreparedQuery(query, id);
        return results.stream()
                .map(MemberRequests::mapMember)
                .collect(Collectors.toList());
    }

    private static User mapUser(Map<String, Object> result) {
        return new User()
                .setId((Integer) result.get("user_id"))
                .setLogin((String) result.get("login"))
                .setFirstname((String) result.get("firstname"))
                .setLastname((String) result.get("lastname"))
                .setPassword(null)
                .setSalt(null)
                .setEmail(null)
                .setApiKey(null)
                .setAdmin((Boolean) result.get("admin"))
                .setStatus((Integer) result.get("status"));
    }

    private static Member mapMember(Map<String, Object> result){
        return new Member()
                .setId((Integer) result.get("id"))
                .setUser(mapUser(result));
    }
}
