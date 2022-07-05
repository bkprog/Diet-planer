package dietplaner.example.dietplaner.recipe.service;

import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.recipe.exceptions.RecipeAlreadyExistException;
import dietplaner.example.dietplaner.recipe.models.RecipeDTO;
import dietplaner.example.dietplaner.recipe.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private void validateWithRecipeName(String recipeName){
        if(recipeRepository.findRecipeByName(recipeName).isPresent()) throw new RecipeAlreadyExistException();
    }

    public void addRecipe(RecipeDTO recipeDTO){
        validateWithRecipeName(recipeDTO.getName());

        recipeRepository.save(Recipe.of(recipeDTO));

    }
}
