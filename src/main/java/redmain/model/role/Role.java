package redmain.model.role;


import lombok.Data;
import lombok.NoArgsConstructor;
import redmain.model.Generatable;

@Data
@NoArgsConstructor
public class Role implements Generatable<Role> {
    private Integer id;
    private Integer position;
    private Integer builtin;
    private String name;
    private Boolean assignable;
    private IssuesVisibility issuesVisibility;
    private UsersVisibility usersVisibility;
    private RolePermissions permissions;
    private TimeEntriesVisibility timeEntriesVisibility;
    private boolean allRolesManaged;
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

    public static void main(String[] args) {
        Role role = new Role();
        role.permissions = new RolePermissions();
        role.permissions.add(RolePermission.ADD_PROJECT);
        role.permissions.add(RolePermission.EDIT_PROJECT);
        role.permissions.add(RolePermission.MANAGE_MEMBERS);

        System.out.println(role.permissions.toString());
    }

    @Override
    public Role read() {
        //TODO получение из БД
        return null;
    }

    @Override
    public Role update() {
        //TODO обновление из БД
        return null;
    }

    @Override
    public Role create() {
        //TODO создание в БД
        return null;
    }
}

