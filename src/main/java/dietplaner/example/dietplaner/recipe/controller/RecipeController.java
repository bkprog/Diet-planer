package dietplaner.example.dietplaner.recipe.controller;

import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.recipe.models.RecipeDTO;
import dietplaner.example.dietplaner.recipe.repository.RecipeRepository;
import dietplaner.example.dietplaner.recipe.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final RecipeService recipeService;

    @PostMapping("recipe/add")
    public void addRecipe(@RequestBody RecipeDTO recipeDTO){
        recipeService.addRecipe(recipeDTO);
    }

    @DeleteMapping("recipe/deleteAll")
    public void deleteRecipe(){
        recipeRepository.deleteAll();
    }

    @GetMapping("recipe/getall")
    public List<Recipe> getAllRecipes(){
       return recipeRepository.findAll();
    }
}
