package dietplaner.example.dietplaner.user.Exceptions.advices;

import dietplaner.example.dietplaner.user.Exceptions.UserLoginAlreadyExistException;
import dietplaner.example.dietplaner.user.Exceptions.UserNotExistException;
import dietplaner.example.dietplaner.user.Exceptions.UserWrongAuthorizationDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class UserAdviceController {


    @ResponseBody
    @ExceptionHandler(value = {
            UserLoginAlreadyExistException.class,

    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflictAdvice(RuntimeException ex) {
        log.warn(String.format("Error:'%s'", ex.getMessage()));
        return ex.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(value = {
            UserWrongAuthorizationDataException.class,
            UserNotExistException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundAdvice(RuntimeException ex) {
        log.warn(String.format("Error:'%s'", ex.getMessage()));
        return ex.getMessage();
    }



}
