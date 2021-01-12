package redmain.db.request;

import redmain.db.managers.Manager;
import redmain.model.role.Role;

import java.util.List;
import java.util.Map;

public class RoleRequest {
    public static List<Role> getAllRoles(){
        String query =  "SELECT * FROM roles";
        List<Map<String, Object>> result = Manager.dbConnection.executePreparedQuery(query);
        result.forEach(map -> {
            Role role = new Role();
            role.setId((map.get("id")));
        });
        return null;
    }
}
