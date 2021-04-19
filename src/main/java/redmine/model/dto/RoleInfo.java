package redmine.model.dto;

import java.util.List;


public class RoleInfo {
    private Integer id;
    private String name;
    private Boolean assignable;
    private Boolean issues_visibility;
    private Boolean users_visibility;
    private List<String> permissions;

    public RoleInfo(Integer id, String name, Boolean assignable, Boolean issues_visibility, Boolean users_visibility, List<String> permissions) {
        this.id = id;
        this.name = name;
        this.assignable = assignable;
        this.issues_visibility = issues_visibility;
        this.users_visibility = users_visibility;
        this.permissions = permissions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAssignable() {
        return assignable;
    }

    public void setAssignable(Boolean assignable) {
        this.assignable = assignable;
    }

    public Boolean getIssues_visibility() {
        return issues_visibility;
    }

    public void setIssues_visibility(Boolean issues_visibility) {
        this.issues_visibility = issues_visibility;
    }

    public Boolean getUsers_visibility() {
        return users_visibility;
    }

    public void setUsers_visibility(Boolean users_visibility) {
        this.users_visibility = users_visibility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleInfo roleInfo = (RoleInfo) o;

        if (id != null ? !id.equals(roleInfo.id) : roleInfo.id != null) return false;
        if (name != null ? !name.equals(roleInfo.name) : roleInfo.name != null) return false;
        if (assignable != null ? !assignable.equals(roleInfo.assignable) : roleInfo.assignable != null) return false;
        if (issues_visibility != null ? !issues_visibility.equals(roleInfo.issues_visibility) : roleInfo.issues_visibility != null)
            return false;
        if (users_visibility != null ? !users_visibility.equals(roleInfo.users_visibility) : roleInfo.users_visibility != null)
            return false;
        return permissions != null ? permissions.equals(roleInfo.permissions) : roleInfo.permissions == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (assignable != null ? assignable.hashCode() : 0);
        result = 31 * result + (issues_visibility != null ? issues_visibility.hashCode() : 0);
        result = 31 * result + (users_visibility != null ? users_visibility.hashCode() : 0);
        result = 31 * result + (permissions != null ? permissions.hashCode() : 0);
        return result;
    }
}
