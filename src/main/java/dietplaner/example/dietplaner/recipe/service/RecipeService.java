package dietplaner.example.dietplaner.recipe.service;

import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.product.service.ProductService;
import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.recipe.exceptions.ProductListIsEmptyException;
import dietplaner.example.dietplaner.recipe.exceptions.RecipeAlreadyExistException;
import dietplaner.example.dietplaner.recipe.exceptions.RecipeNotExistException;
import dietplaner.example.dietplaner.recipe.models.RecipeDTO;
import dietplaner.example.dietplaner.recipe.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final ProductService productService;

    private void validateWithRecipeName(String recipeName){
        if(recipeRepository.findRecipeByName(recipeName).isPresent()) throw new RecipeAlreadyExistException();
    }

    private void validateIfRecipeExistById(Long recipeId){
        recipeRepository.findRecipeByRecipeId(recipeId).stream().findFirst().orElseThrow(RecipeNotExistException::new);
    }

    private void validateIfProductListIsNull(List<Long> productsIds){
        if(productsIds.isEmpty()){
            throw new ProductListIsEmptyException();
        }
    }

    private List<Product> getRecipeListFromRecipeIdList(List<Long> productsIds){
        List<Product> products= new ArrayList<>();
        validateIfProductListIsNull(productsIds);

        productsIds.stream().forEach(id->{
            productService.validateIfProductExistById(id);
            Product product=productService.findProductbyId(id);
            products.add(product);
        });
        return products;
    }

    public Recipe getRecipeById(Long recipeId){
        return recipeRepository.findRecipeByRecipeId(recipeId).stream().findFirst().orElseThrow(RecipeNotExistException::new);
    }


    public void addRecipe(RecipeDTO recipeDTO){
        validateWithRecipeName(recipeDTO.getName());
        Recipe recipe=Recipe.of((recipeDTO));
        List<Long> recipeIds= recipeDTO.getProducts();
        List<Product> productList;
        productList= getRecipeListFromRecipeIdList(recipeIds);
        recipe.setProducts(productList);
        recipeRepository.save(recipe);
    }

    @Transactional
    public void deleteRecipe(Long recipeId){
        validateIfRecipeExistById(recipeId);
        recipeRepository.deleteByRecipeId(recipeId);
    }


}
