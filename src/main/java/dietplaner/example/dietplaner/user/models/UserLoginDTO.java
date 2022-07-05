package dietplaner.example.dietplaner.user.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    private String login;
    private String password;
}
