package dietplaner.example.dietplaner.alergen.exceptions;

public class ProductAlreadyHaveThisAlergenSavedException extends RuntimeException{

    public ProductAlreadyHaveThisAlergenSavedException(){
        super("Ten alergen jest już przypisany do tego produktu");
    }

}
