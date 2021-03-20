package redmine.db.request;

import redmine.managers.Manager;
import redmine.model.project.Project;
import redmine.model.role.Role;
import redmine.model.user.Member;
import redmine.model.user.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemberRequests {

    public static List<Member> getByProject(Project project) {
        String query = "SELECT * FROM members m " +
                "INNER JOIN users u ON u.id=m.user_id WHERE m.project_id=?";
        List<Map<String, Object>> results = Manager.dbConnection.executePreparedQuery(query, project.getId());
        return results.stream()
                .map(MemberRequests::mapMember)
                .collect(Collectors.toList());
    }

    public static Member createMember(Project project, User user) {
        Member member = new Member()
                .setUser(user);
        String query = "INSERT INTO members" +
                "(id, user_id, project_id) " +
                "VALUES(DEFAULT,?,?) RETURNING id;";
        List<Map<String, Object>> result = Manager.dbConnection.executePreparedQuery(query,
                user.getId(),
                project.getId()
        );
        member.setId((Integer) result.get(0).get("id"));
        return member;
    }

    public static void addRole(Member member, Role role) {
        String query = "INSERT INTO member_roles" +
                "(id, member_id, role_id) " +
                "VALUES(DEFAULT,?,?) RETURNING id;";
        List<Map<String, Object>> result = Manager.dbConnection.executePreparedQuery(query,
                member.getId(),
                role.getId()
        );
        System.out.println("member role: " + result.get(0).get("id"));
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

    private static Member mapMember(Map<String, Object> result) {
        return new Member()
                .setId((Integer) result.get("id"))
                .setUser(mapUser(result));
    }
}
