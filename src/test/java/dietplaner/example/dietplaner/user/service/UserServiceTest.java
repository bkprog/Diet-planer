package dietplaner.example.dietplaner.user.service;

import dietplaner.example.dietplaner.product.exceptions.ProductAlreadyExistException;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.exceptions.UserNotExistException;
import dietplaner.example.dietplaner.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @AfterEach
    public void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    void findUserById_should_find() {
        //given
        DefaultUser user= userRepository.save(new DefaultUser("login","password","name","surname","email"));
        //when
        DefaultUser result = userService.findUserById(user.getUserId());
        //then
        Assertions.assertEquals("login",result.getLogin());
    }

    @Test
    void findUserById_should_throw_UserNotExistException() {
        //given

        //when
        Exception thrown=Assertions.assertThrows(UserNotExistException.class,()->{
            userService.findUserById(1L);
        });
        //then
        Assertions.assertEquals("Uzytkownik o tym id nie istnieje",thrown.getMessage());
    }
}