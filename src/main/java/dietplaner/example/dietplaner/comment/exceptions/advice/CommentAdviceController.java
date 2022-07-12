package dietplaner.example.dietplaner.comment.exceptions.advice;

import dietplaner.example.dietplaner.comment.exceptions.CommentNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class CommentAdviceController {

    @ResponseBody
    @ExceptionHandler(value = {
            CommentNotExistException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundAdvice(RuntimeException ex) {
        log.warn(String.format("Error:'%s'", ex.getMessage()));
        return ex.getMessage();
    }


}
