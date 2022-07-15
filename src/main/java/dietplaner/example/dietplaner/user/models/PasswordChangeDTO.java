package dietplaner.example.dietplaner.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PasswordChangeDTO {
    private String login;
    private String password;
    private String newPassword;
}
