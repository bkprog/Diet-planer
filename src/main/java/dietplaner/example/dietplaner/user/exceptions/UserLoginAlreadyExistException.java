package dietplaner.example.dietplaner.user.exceptions;

public class UserLoginAlreadyExistException extends RuntimeException{
    public UserLoginAlreadyExistException(){super("Wprowadzony login jest już zajęty, wybierz inny");}
}
