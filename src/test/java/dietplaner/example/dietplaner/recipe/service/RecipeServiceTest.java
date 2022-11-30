package dietplaner.example.dietplaner.recipe.service;

import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.product.exceptions.ProductAlreadyExistException;
import dietplaner.example.dietplaner.product.exceptions.ProductNotExistException;
import dietplaner.example.dietplaner.product.repository.ProductRepository;
import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.recipe.exceptions.ProductListIsEmptyException;
import dietplaner.example.dietplaner.recipe.exceptions.RecipeAlreadyExistException;
import dietplaner.example.dietplaner.recipe.exceptions.RecipeNotExistException;
import dietplaner.example.dietplaner.recipe.models.RecipeDTO;
import dietplaner.example.dietplaner.recipe.repository.RecipeRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecipeServiceTest {

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeService recipeService;
    @Autowired
    ProductRepository productRepository;


    @AfterEach
    void tearDown(){

        recipeRepository.deleteAll();
    }

    @Test
    void getRecipeById_should_get() {
        //given
        Recipe recipe= recipeRepository.save(new Recipe("przepis","desc"));
        //when
        Recipe result= recipeService.getRecipeById(recipe.getRecipeId());
        //then
        Assertions.assertEquals("przepis",result.getName());
        Assertions.assertEquals("desc",result.getDescription());
    }

    @Test
    void getRecipeById_should_throw_RecipeNotExistException(){
        //given

        //when
        Exception thrown=Assertions.assertThrows(RecipeNotExistException.class,()->{
            recipeService.getRecipeById(1L);
        });
        //then
        Assertions.assertEquals("Taki przepis nie istnieje!",thrown.getMessage());
    }


    @Test
    void addRecipe_should_add() {
        //given
        Product product1= productRepository.save(new Product("prod1",10L,10L,10L,10L));
        Product product2= productRepository.save(new Product("prod2",10L,10L,10L,10L));
        List<Long> productIdsList= Arrays.asList(product1.getId(),product2.getId());
        RecipeDTO dto= new RecipeDTO("name","desc",productIdsList);
        //when
        Recipe result= recipeService.addRecipe(dto);
        //then
        Assertions.assertEquals(dto.getName(),result.getName());
        Assertions.assertEquals(dto.getDescription(),result.getDescription());
    }

    @Test
    void addRecipe_should_throw_RecipeAlreadyExistException(){
        //given
        Product product1= productRepository.save(new Product("prod1",10L,10L,10L,10L));
        Product product2= productRepository.save(new Product("prod2",10L,10L,10L,10L));
        List<Long> productIdsList= Arrays.asList(product1.getId(),product2.getId());
        RecipeDTO dto= new RecipeDTO("name","desc",productIdsList);
        recipeRepository.save(Recipe.of(dto));
        //when
        Exception thrown=Assertions.assertThrows(RecipeAlreadyExistException.class,()->{
            recipeService.addRecipe(dto);
        });
        //then
        Assertions.assertEquals("Wprowadzona nazwa przepisu jest już zajęta, wybierz inny",thrown.getMessage());
    }

    @Test
    void addRecipe_should_throw_ProductListIsEmptyException(){
        //given
        RecipeDTO dto= new RecipeDTO("name","desc",new ArrayList<>());
        //when
        Exception thrown=Assertions.assertThrows(ProductListIsEmptyException.class,()->{
            recipeService.addRecipe(dto);
        });
        //then
        Assertions.assertEquals("Nie możesz stworzyć przepisu bez składników",thrown.getMessage());
    }

    @Test
    void addRecipe_should_throw_ProductNotExistException(){
        //given
        Product product1= productRepository.save(new Product("prod1",10L,10L,10L,10L));
        Product product2= productRepository.save(new Product("prod2",10L,10L,10L,10L));
        List<Long> productIdsList= Arrays.asList(product1.getId(),product2.getId(),product2.getId()+1);
        RecipeDTO dto= new RecipeDTO("name","desc",productIdsList);
        //when
        Exception thrown=Assertions.assertThrows(ProductNotExistException.class,()->{
            recipeService.addRecipe(dto);
        });
        //then
        Assertions.assertEquals("Produkt nie istnieje",thrown.getMessage());
    }

    @Test
    void deleteRecipe_should_delete() {
        //given
        Product product1= productRepository.save(new Product("prod1",10L,10L,10L,10L));
        Product product2= productRepository.save(new Product("prod2",10L,10L,10L,10L));
        List<Long> productIdsList= Arrays.asList(product1.getId(),product2.getId(),product2.getId()+1);
        RecipeDTO dto= new RecipeDTO("name","desc",productIdsList);
        Recipe saved= recipeRepository.save(Recipe.of(dto));
        List<Recipe> recipesBeforeDelete= recipeRepository.findAll();
        Assertions.assertFalse(recipesBeforeDelete.isEmpty());
        //when
        recipeService.deleteRecipe(saved.getRecipeId());
        //then
        List<Recipe> recipesAfterDelete=recipeRepository.findAll();
        Assertions.assertTrue(recipesAfterDelete.isEmpty());
    }
    @Test
    void deleteRecipe_should_throw_RecipeNotExistException(){
        //given

        //when
        Exception thrown=Assertions.assertThrows(RecipeNotExistException.class,()->{
            recipeService.deleteRecipe(1L);
        });
        //then
        Assertions.assertEquals("Taki przepis nie istnieje!",thrown.getMessage());
    }
}