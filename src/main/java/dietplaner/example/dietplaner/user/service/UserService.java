package dietplaner.example.dietplaner.user.service;

import dietplaner.example.dietplaner.user.Exceptions.UserNotExistException;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;




@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public DefaultUser findUserById(Long userId){
        return userRepository.findDefaultUserByUserId(userId).stream().findFirst().orElseThrow(UserNotExistException::new);
    }


}
