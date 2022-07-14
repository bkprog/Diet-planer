package dietplaner.example.dietplaner.alergen.exceptions;

public class AlergenAlreadyExistException extends RuntimeException{

    public AlergenAlreadyExistException(){
        super("Ten alergen juz istnieje");
    }
}
