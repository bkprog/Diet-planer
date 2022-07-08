package dietplaner.example.dietplaner.recipe.exceptions;


public class RecipeAlreadyExistException extends RuntimeException{

    public RecipeAlreadyExistException(){super("Wprowadzona nazwa przepisu jest już zajęta, wybierz inny");}
}
