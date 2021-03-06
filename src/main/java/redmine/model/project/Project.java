package redmine.model.project;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import redmine.utils.StringGenerators;

import java.time.Instant;

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


    public static Project generate(){
        return new Project()
                .setName("Ksyu" + StringGenerators.randomEnglishString(10))
                .setDescription(StringGenerators.randomEnglishString(20))
                .setIsPublic(true)
                .setIdentifier(StringGenerators.randomEnglishLowerString(10))
                .setStatus(1);
    }
}
