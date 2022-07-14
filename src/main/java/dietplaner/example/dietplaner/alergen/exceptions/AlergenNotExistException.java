package dietplaner.example.dietplaner.alergen.exceptions;


public class AlergenNotExistException extends RuntimeException {

    public AlergenNotExistException(){
        super("Alergen nie istnieje");
    }
}
