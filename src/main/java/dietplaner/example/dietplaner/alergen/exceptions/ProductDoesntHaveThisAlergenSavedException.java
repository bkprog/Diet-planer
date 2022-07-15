package dietplaner.example.dietplaner.alergen.exceptions;

public class ProductDoesntHaveThisAlergenSavedException extends RuntimeException{

    public ProductDoesntHaveThisAlergenSavedException(){
        super("Produkt nie ma przypisanego podanego alergenu, wiec nie mozesz go usunac");
    }

}
