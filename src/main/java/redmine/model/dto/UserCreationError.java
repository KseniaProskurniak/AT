package redmine.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserCreationError {
    private Integer id;
    private String name;
   @NonNull
    private String firstName;
    private String lastName;

}
