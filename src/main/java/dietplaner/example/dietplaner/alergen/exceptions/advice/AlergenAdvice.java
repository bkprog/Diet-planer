package dietplaner.example.dietplaner.alergen.exceptions.advice;

import dietplaner.example.dietplaner.alergen.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class AlergenAdvice {

    @ResponseBody
    @ExceptionHandler(value = {
            UserAlreadyHaveThisAlergenSavedException.class,
            AlergenAlreadyExistException.class,
            ProductAlreadyHaveThisAlergenSavedException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflictAdvice(RuntimeException ex) {
        log.warn(String.format("Error:'%s'", ex.getMessage()));
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = {
            AlergenNotExistException.class,
            UserDoesntHaveThisAlergenSavedException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundAdvice(RuntimeException ex) {
        log.warn(String.format("Error:'%s'", ex.getMessage()));
        return ex.getMessage();
    }
}
