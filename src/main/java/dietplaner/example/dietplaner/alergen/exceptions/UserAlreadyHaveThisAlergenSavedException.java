package dietplaner.example.dietplaner.alergen.exceptions;

public class UserAlreadyHaveThisAlergenSavedException extends RuntimeException{

    public UserAlreadyHaveThisAlergenSavedException(){
        super("Ten uzytkownik ma już zapisany ten alergen");
    }
}
