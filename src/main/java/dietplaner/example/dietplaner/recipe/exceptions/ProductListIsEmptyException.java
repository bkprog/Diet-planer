package dietplaner.example.dietplaner.recipe.exceptions;

public class ProductListIsEmptyException extends RuntimeException{

    public ProductListIsEmptyException(){
        super("Nie możesz stworzyć przepisu bez składników");
    }
}
