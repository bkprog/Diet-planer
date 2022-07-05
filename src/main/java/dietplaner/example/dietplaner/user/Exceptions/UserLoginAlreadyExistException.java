package dietplaner.example.dietplaner.user.Exceptions;

public class UserLoginAlreadyExistException extends RuntimeException{
    public UserLoginAlreadyExistException(){super("Wprowadzony login jest już zajęty, wybierz inny");}
}
