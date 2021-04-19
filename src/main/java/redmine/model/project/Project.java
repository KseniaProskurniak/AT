package redmine.model.project;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import redmine.db.request.MemberRequests;
import redmine.db.request.ProjectRequests;
import redmine.model.role.Role;
import redmine.model.user.Member;
import redmine.model.user.User;
import redmine.utils.StringGenerators;

import java.time.Instant;
import java.util.List;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project {
    Integer id;
    String name;
    String description;
    String homepage;
    Boolean isPublic;
    Project parent;
    Instant createdOn;
    Instant updatedOn;
    String identifier;
    Integer status;
    Integer lft;
    Integer rgt;
    Boolean inheritMembers;
    Integer defaultVersionId;
    Integer defaultAssignedToId;
    List<Member> members;


    public static Project generate() {
        return new Project()
                .setName("Ksyu" + StringGenerators.randomEnglishString(10))
                .setDescription(StringGenerators.randomEnglishString(20))
                .setIsPublic(true)
                .setIdentifier(StringGenerators.randomEnglishLowerString(10))
                .setStatus(1);
    }

    public Project create() {
        return ProjectRequests.create(this);
    }

    public void delete() {

    }

    public Project addMember(User user, List<Role> roles) {
        Member member = MemberRequests.createMember(this, user);
        roles.forEach(member::addRole);
        return this;
    }

    public List<Member> getMembers() {
        if (members == null) {
            members = MemberRequests.getByProject(this);
        }
        return members;
    }

}
