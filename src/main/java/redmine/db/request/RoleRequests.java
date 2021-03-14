package redmine.db.request;

import redmine.managers.Manager;
import redmine.model.role.IssuesVisibility;
import redmine.model.role.Role;
import redmine.model.role.RolePermissions;
import redmine.model.role.TimeEntriesVisibility;
import redmine.model.user.Member;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoleRequests {

    public static List<Role> getAllRoles() {
        String query = "SELECT * FROM roles";
        List<Map<String, Object>> result = Manager.dbConnection.executeQuery(query);
        return result.stream()
                .map(map -> {
                    Role role = new Role();
                    role.setId((Integer) map.get("id"));
                    role.setPosition((Integer) map.get("position"));
                    role.setBuiltin((Integer) map.get("builtin"));
                    role.setName((String) map.get("name"));
                    role.setAssignable((Boolean) map.get("assignable"));
                    role.setIssuesVisibility(
                            IssuesVisibility.valueOf(((String) map.get("issues_visibility")).toUpperCase())
                    );
                    role.setPermissions(RolePermissions.of((String) map.get("permissions")));
                    role.setTimeEntriesVisibility(
                            TimeEntriesVisibility.valueOf(((String) map.get("time_entries_visibility")).toUpperCase())
                    );
                    role.setAllRolesManaged((Boolean) map.get("all_roles_managed"));
                    role.setSettings((String) map.get("settings"));
                    return role;
                })
                .collect(Collectors.toList());

    }

    public static Role getRole(Role objectRole) {
        return getAllRoles().stream()
                .filter(role -> {
                    if (objectRole.getId() == null) {
                        return objectRole.getName().equals(role.getName());
                    } else {
                        return objectRole.getId().equals(role.getId());
                    }
                })
                .findFirst()
                .orElse(null);
    }

    public static List<Role> getRolesByMember(Member member) {
        String query = "SELECT * FROM roles r INNER JOIN member_roles mr ON r.id = mr.role_id WHERE mr.member_id=?;";
        List<Map<String, Object>> results = Manager.dbConnection.executePreparedQuery(query, member.getId());
        return results.stream()
                .map(r -> new Role()
                        .setId((Integer) r.get("id"))
                        .setName((String) r.get("name")))
                .collect(Collectors.toList());
    }

    public static Role addRole(Role role) {
        String query = "INSERT INTO public.roles " +
                "(id, name, position, assignable, builtin, permissions, issues_visibility, users_visibility, " +
                "time_entries_visibility, all_roles_managed, settings) " +
                "VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?) RETURNING id;";
        List<Map<String, Object>> result = Manager.dbConnection.executePreparedQuery(query,
                role.getName(),
                role.getPosition(),
                role.getAssignable(),
                role.getBuiltin(),
                role.getPermissions().toString(),
                role.getIssuesVisibility().toString(),
                role.getUsersVisibility().toString(),
                role.getTimeEntriesVisibility().toString(),
                role.isAllRolesManaged(),
                role.getSettings()
        );
        role.setId((Integer) result.get(0).get("id"));
        return role;
    }


    public static Role updateRole(Role role) {
        String query = "UPDATE public.roles " +
                "SET position=?, assignable=?, builtin=?, " +
                "permissions=?, issues_visibility=?, users_visibility=?, time_entries_visibility=?, all_roles_managed=?, settings=? " +
                "WHERE name=? RETURNING id;";
        List<Map<String, Object>> result = Manager.dbConnection.executePreparedQuery(query,
                role.getPosition(),
                role.getAssignable(),
                role.getBuiltin(),
                role.getPermissions().toString(),
                role.getIssuesVisibility().toString(),
                role.getUsersVisibility().toString(),
                role.getTimeEntriesVisibility().toString(),
                role.isAllRolesManaged(),
                role.getSettings(),
                role.getName()
        );
        role.setId((Integer) result.get(0).get("id"));
        return role;
    }
}
