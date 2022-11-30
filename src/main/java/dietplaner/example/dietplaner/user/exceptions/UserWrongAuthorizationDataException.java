package dietplaner.example.dietplaner.user.exceptions;

public class UserWrongAuthorizationDataException extends RuntimeException{

    public UserWrongAuthorizationDataException(){super("Niepoprawny login lub hasło");}

}
