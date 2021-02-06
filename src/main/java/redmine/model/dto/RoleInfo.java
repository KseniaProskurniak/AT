package redmine.model.dto;

import lombok.Data;

import java.util.List;
@Data
public class RoleInfo {
    private Integer id;
    private String name;
    private Boolean assignable;
    private Boolean issues_visibility;
    private Boolean users_visibility;
    private List<String> permissions;
}
