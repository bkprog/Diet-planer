package dietplaner.example.dietplaner.alergen.exceptions;

public class UserDoesntHaveThisAlergenSavedException extends RuntimeException{

    public UserDoesntHaveThisAlergenSavedException(){
        super("Użytkownik nie ma takiego alergenu, nie mozesz go usunac");
    }
}
