package redmain.model.role;


import lombok.Data;
import lombok.NoArgsConstructor;
import redmain.db.request.RoleRequests;
import redmain.model.Generatable;
import redmain.utils.StringGenerators;

import java.security.Permissions;
import java.util.HashSet;

@Data
@NoArgsConstructor
public class Role implements Generatable<Role> {
    private Integer id;
    private Integer position = 1;
    private Integer builtin = 0;
    private String name ="Auto" + StringGenerators.randomEnglishString(8);
    private Boolean assignable = true;
    private IssuesVisibility issuesVisibility = IssuesVisibility.DEFAULT;
    private UsersVisibility usersVisibility = UsersVisibility.ALL;
    private RolePermissions permissions = new RolePermissions(new HashSet<>());
    private TimeEntriesVisibility timeEntriesVisibility = TimeEntriesVisibility.ALL;
    private boolean allRolesManaged = true;
    private String settings = "--- !ruby/hash:ActiveSupport::HashWithIndifferentAccess\n" +
            "permissions_all_trackers: !ruby/hash:ActiveSupport::HashWithIndifferentAccess\n" +
            "  view_issues: '1'\n" +
            "  add_issues: '1'\n" +
            "  edit_issues: '1'\n" +
            "  add_issue_notes: '1'\n" +
            "  delete_issues: '1'\n" +
            "permissions_tracker_ids: !ruby/hash:ActiveSupport::HashWithIndifferentAccess\n" +
            "  view_issues: []\n" +
            "  add_issues: []\n" +
            "  edit_issues: []\n" +
            "  add_issue_notes: []\n" +
            "  delete_issues: []\n";



    @Override
    public Role read() {
        Role role = RoleRequests.getRole(this);
        this.id = role.id;
        this.position = role.position;
        this.builtin = role.builtin;
        this.name = role.name;
        this.assignable = role.assignable;
        this.issuesVisibility = role.issuesVisibility;
        this.usersVisibility = role.usersVisibility;
        this.timeEntriesVisibility = role.timeEntriesVisibility;
        this.allRolesManaged = role.allRolesManaged;
        this.settings = role.settings;
        return this;
    }

    @Override
    public Role update() {
        return RoleRequests.updateRole(this);
    }

    @Override
    public Role create() {
        //TODO создание в БД
        return RoleRequests.addRole(this);
    }
}

