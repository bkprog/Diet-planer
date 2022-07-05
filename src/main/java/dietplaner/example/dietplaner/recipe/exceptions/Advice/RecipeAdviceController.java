package dietplaner.example.dietplaner.recipe.exceptions.Advice;

import dietplaner.example.dietplaner.product.exceptions.ProductAlreadyExistException;
import dietplaner.example.dietplaner.recipe.exceptions.RecipeAlreadyExistException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class RecipeAdviceController {

    @ResponseBody
    @ExceptionHandler(value = {
            RecipeAlreadyExistException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflictAdvice(RuntimeException ex) {
        log.warn(String.format("Error:'%s'", ex.getMessage()));
        return ex.getMessage();
    }
}
