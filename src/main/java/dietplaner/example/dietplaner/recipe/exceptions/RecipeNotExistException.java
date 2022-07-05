package dietplaner.example.dietplaner.recipe.exceptions;

public class RecipeNotExistException extends  RuntimeException{
    public RecipeNotExistException(){super("Taki przepis nie istnieje!");}

}
