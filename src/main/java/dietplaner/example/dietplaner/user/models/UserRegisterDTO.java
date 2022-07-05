package dietplaner.example.dietplaner.user.models;

import dietplaner.example.dietplaner.user.entity.DefaultUser;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDTO {

    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;



}
