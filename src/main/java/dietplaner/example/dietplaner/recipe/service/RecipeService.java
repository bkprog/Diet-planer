package dietplaner.example.dietplaner.recipe.service;

import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.recipe.exceptions.RecipeAlreadyExistException;
import dietplaner.example.dietplaner.recipe.exceptions.RecipeNotExistException;
import dietplaner.example.dietplaner.recipe.models.RecipeDTO;
import dietplaner.example.dietplaner.recipe.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private void validateWithRecipeName(String recipeName){
        if(recipeRepository.findRecipeByName(recipeName).isPresent()) throw new RecipeAlreadyExistException();
    }

    private void validateIfRecipeExistById(Long recipeId){
        recipeRepository.findRecipeByRecipeId(recipeId).stream().findFirst().orElseThrow(RecipeNotExistException::new);
    }

    public Recipe getRecipeById(Long recipeId){
        return recipeRepository.findRecipeByRecipeId(recipeId).stream().findFirst().orElseThrow(RecipeNotExistException::new);
    }


    public void addRecipe(RecipeDTO recipeDTO){
        validateWithRecipeName(recipeDTO.getName());
        recipeRepository.save(Recipe.of(recipeDTO));
    }

    @Transactional
    public void deleteRecipe(Long recipeId){
        validateIfRecipeExistById(recipeId);
        recipeRepository.deleteByRecipeId(recipeId);
    }


}
