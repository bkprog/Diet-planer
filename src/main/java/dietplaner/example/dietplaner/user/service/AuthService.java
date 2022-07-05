package dietplaner.example.dietplaner.user.service;

import dietplaner.example.dietplaner.user.Exceptions.UserLoginAlreadyExistException;
import dietplaner.example.dietplaner.user.Exceptions.UserWrongAuthorizationDataException;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.models.UserLoginDTO;
import dietplaner.example.dietplaner.user.models.UserRegisterDTO;
import dietplaner.example.dietplaner.user.models.UserResponseDTO;
import dietplaner.example.dietplaner.user.repository.UserRepository;
import dietplaner.example.dietplaner.utils.Passwordhash;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private void validateUserWithLogin(String login) {
        if (userRepository.findDefaultUserByLogin(login).isPresent()) {
            throw new UserLoginAlreadyExistException();
        }
    }

    public void register(UserRegisterDTO userRegisterDTO){

        validateUserWithLogin(userRegisterDTO.getLogin());

        userRepository.save(DefaultUser.of(UserRegisterDTO.builder()
                .name(userRegisterDTO.getName())
                .surname(userRegisterDTO.getSurname())
                .email(userRegisterDTO.getEmail())
                .login(userRegisterDTO.getLogin())
                .password(Passwordhash.passwordHash(userRegisterDTO.getPassword()))
                .build()));
    }

    public UserResponseDTO login(UserLoginDTO userLoginDTO){

        DefaultUser defaultUser= userRepository.findDefaultUserByLoginAndPassword(userLoginDTO.getLogin(), Passwordhash.passwordHash(userLoginDTO.getPassword()))
                .stream().findFirst().orElseThrow(UserWrongAuthorizationDataException::new);

        return UserResponseDTO.of(defaultUser);

    }
}
