package dietplaner.example.dietplaner.user.Exceptions;

public class UserNotExistException extends RuntimeException{

    public UserNotExistException(){
        super("Uzytkownik o tym id nie istnieje");
    }
}
