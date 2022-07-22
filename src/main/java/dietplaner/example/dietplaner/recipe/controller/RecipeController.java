package dietplaner.example.dietplaner.recipe.controller;

import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.recipe.models.RecipeDTO;
import dietplaner.example.dietplaner.recipe.models.RecipeFilterDTO;
import dietplaner.example.dietplaner.recipe.repository.RecipeRepository;
import dietplaner.example.dietplaner.recipe.service.RecipeFilterService;
import dietplaner.example.dietplaner.recipe.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final RecipeService recipeService;

    private final RecipeFilterService recipeFilterService;

    @PostMapping("/add")
    public void addRecipe(@RequestBody RecipeDTO recipeDTO){
        recipeService.addRecipe(recipeDTO);
    }

    @DeleteMapping("/deleteAll")
    public void deleteRecipe(){
        recipeRepository.deleteAll();
    }

    @GetMapping("/getall")
    public List<Recipe> getAllRecipes(){
       return recipeRepository.findAll();
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestBody Long recipeId){
        recipeService.deleteRecipe(recipeId);
    }

    @GetMapping("/returnFiltrRecipe")
    public List<Recipe> getfiltredRecipes(@RequestBody RecipeFilterDTO recipeFilterDTO){
        return recipeFilterService.filterRecipes(recipeFilterDTO);
    }




}
