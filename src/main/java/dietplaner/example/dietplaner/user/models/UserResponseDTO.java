package dietplaner.example.dietplaner.user.models;

import dietplaner.example.dietplaner.user.entity.DefaultUser;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long user_id;
    private String name;
    private String surname;
    private String email;
    private String login;

    public static UserResponseDTO of(DefaultUser defaultUser){

        return UserResponseDTO.builder()
                .user_id(defaultUser.getUser_id())
                .name(defaultUser.getName())
                .surname(defaultUser.getSurname())
                .login(defaultUser.getLogin())
                .email(defaultUser.getEmail())
                .build();
    }
}
