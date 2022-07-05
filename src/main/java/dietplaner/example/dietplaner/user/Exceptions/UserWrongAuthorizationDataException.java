package dietplaner.example.dietplaner.user.Exceptions;

public class UserWrongAuthorizationDataException extends RuntimeException{

    public UserWrongAuthorizationDataException(){super("Niepoprawny login lub hasło");}

}
