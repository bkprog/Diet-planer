package dietplaner.example.dietplaner.dailyconsumption.exceptions.advice;

import dietplaner.example.dietplaner.dailyconsumption.exceptions.DailyConsumptionAlreadyExistException;
import dietplaner.example.dietplaner.dailyconsumption.exceptions.DailyConsumptionNotExistException;
import dietplaner.example.dietplaner.dailyconsumption.exceptions.UserDoesNotHaveAnyDailyConsumptionsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class DailyConsumptionAdvice {

    @ResponseBody
    @ExceptionHandler(value = {
            DailyConsumptionAlreadyExistException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflictAdvice(RuntimeException ex) {
        log.warn(String.format("Error:'%s'", ex.getMessage()));
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = {
            DailyConsumptionNotExistException.class,
            UserDoesNotHaveAnyDailyConsumptionsException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundAdvice(RuntimeException ex){
        log.warn(String.format("Error: '%s",ex.getMessage()));
        return ex.getMessage();

    }

}
