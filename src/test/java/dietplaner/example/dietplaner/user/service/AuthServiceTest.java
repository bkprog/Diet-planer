package dietplaner.example.dietplaner.user.service;

import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.exceptions.UserLoginAlreadyExistException;
import dietplaner.example.dietplaner.user.exceptions.UserWrongAuthorizationDataException;
import dietplaner.example.dietplaner.user.models.PasswordChangeDTO;
import dietplaner.example.dietplaner.user.models.UserLoginDTO;
import dietplaner.example.dietplaner.user.models.UserRegisterDTO;
import dietplaner.example.dietplaner.user.models.UserResponseDTO;
import dietplaner.example.dietplaner.user.repository.UserRepository;
import dietplaner.example.dietplaner.utils.Passwordhash;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthServiceTest {

    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    void register_should_register() {
        //given
        UserRegisterDTO dto= new UserRegisterDTO("name","surname","email","login","password");
        //when
        DefaultUser result=authService.register(dto);
        //then
        Assertions.assertEquals("name",result.getName());

    }

    @Test
    void register_should_throw_UserLoginAlreadyExistException(){
        //given
        userRepository.save(new DefaultUser("login","password","name","surname","email"));
        UserRegisterDTO dto= new UserRegisterDTO("name","surname","email","login","password");
        ///when
        Exception thrown=Assertions.assertThrows(UserLoginAlreadyExistException.class,()->{
            authService.register(dto);
        });
        //then
        Assertions.assertEquals("Wprowadzony login jest już zajęty, wybierz inny",thrown.getMessage());
    }


    @Test
    void login_should_login() {
        //given
        DefaultUser user = userRepository.save(new DefaultUser("login", Passwordhash.passwordHash("password"), "name", "surname", "email"));
        UserLoginDTO dto= new UserLoginDTO(user.getLogin(),"password");
        //when
        UserResponseDTO result= authService.login(dto);
        //then
        Assertions.assertEquals(user.getUserId(),result.getUser_id());
    }

    @Test
    void login_should_throw_UserWrongAuthorizationDataExcepion(){
        //given
        DefaultUser user = userRepository.save(new DefaultUser("login", Passwordhash.passwordHash("password"), "name", "surname", "email"));
        UserLoginDTO dto= new UserLoginDTO(user.getLogin(),"wrongpassword");
        //when
        Exception thrown= Assertions.assertThrows(UserWrongAuthorizationDataException.class,()->{
            authService.login(dto);
        });
        //then
        Assertions.assertEquals("Niepoprawny login lub hasło",thrown.getMessage());
    }


    @Test
    void changePassword_should_change() {
        //given
        DefaultUser user = userRepository.save(new DefaultUser("login", Passwordhash.passwordHash("password"), "name", "surname", "email"));
        PasswordChangeDTO dto= new PasswordChangeDTO(user.getLogin(),"password","newpassword");
        //when
        authService.changePassword(dto);
        DefaultUser result= userRepository.findDefaultUserByUserId(user.getUserId()).get();
        //then
        Assertions.assertEquals(result.getPassword(),Passwordhash.passwordHash(dto.getNewPassword()));
    }

    @Test
    void changePassword_should_throw_UserWrongAuthorizationDataException(){
        //given
        DefaultUser user = userRepository.save(new DefaultUser("login", Passwordhash.passwordHash("password"), "name", "surname", "email"));
        PasswordChangeDTO dto= new PasswordChangeDTO(user.getLogin(),"wrongpassword","newpassword");
        //when
        Exception thrown= Assertions.assertThrows(UserWrongAuthorizationDataException.class,()->{
            authService.changePassword(dto);
        });
        //then
        Assertions.assertEquals("Niepoprawny login lub hasło",thrown.getMessage());

    }

}