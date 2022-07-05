package dietplaner.example.dietplaner.user.controller;

import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.models.UserLoginDTO;
import dietplaner.example.dietplaner.user.models.UserRegisterDTO;
import dietplaner.example.dietplaner.user.models.UserResponseDTO;
import dietplaner.example.dietplaner.user.repository.UserRepository;
import dietplaner.example.dietplaner.user.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody UserRegisterDTO userRegisterDTO){
        authService.register(userRegisterDTO);
    }

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody UserLoginDTO userLoginDTO){
        return authService.login(userLoginDTO);
    }

    @DeleteMapping("/deleteall")
    public void delete(){
        userRepository.deleteAll();
    }
    @GetMapping("/getall")
    public List<DefaultUser> getall(){
        return userRepository.findAll();
    }

}
