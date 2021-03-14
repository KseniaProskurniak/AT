package redmine.model.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import redmine.db.request.MemberRequests;
import redmine.db.request.RoleRequests;
import redmine.model.role.Role;

import java.util.List;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Member {
    Integer id;
    User user;
    List<Role> roles;

    public List<Role> getRoles() {
        if (roles == null) {
            roles = RoleRequests.getRolesByMember(this);
        }
        return roles;
    }

    public Member addRole(Role role) {
        MemberRequests.addRole(this, role);
        return this;
    }
}
