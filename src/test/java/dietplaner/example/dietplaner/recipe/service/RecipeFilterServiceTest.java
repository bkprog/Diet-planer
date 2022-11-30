package dietplaner.example.dietplaner.recipe.service;

import dietplaner.example.dietplaner.alergen.entity.Alergen;
import dietplaner.example.dietplaner.alergen.repository.AlergenRepository;
import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.product.exceptions.ProductNotExistException;
import dietplaner.example.dietplaner.product.repository.ProductRepository;
import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.recipe.models.RecipeDTO;
import dietplaner.example.dietplaner.recipe.models.RecipeFilterDTO;
import dietplaner.example.dietplaner.recipe.repository.RecipeRepository;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.exceptions.UserNotExistException;
import dietplaner.example.dietplaner.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecipeFilterServiceTest {

    @Autowired
    RecipeFilterService recipeFilterService;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AlergenRepository alergenRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RecipeService recipeService;

    @AfterEach
    void tearDown(){
        recipeRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
        alergenRepository.deleteAll();
    }

    @Test
    void filterRecipes_should_filter() {
        //given
        Alergen alergen1= alergenRepository.save(new Alergen("alergen1"));
        Alergen alergen2= alergenRepository.save(new Alergen("alergen2"));

        List<Alergen> alergensOfUser= new ArrayList<>();
        alergensOfUser.add(alergen1);
        alergensOfUser.add(alergen2);

        List<Alergen> alergensForProduct1 = new ArrayList<>();
        alergensForProduct1.add(alergen1);
        List<Alergen> alergensForProduct3 = new ArrayList<>();
        alergensForProduct3.add(alergen2);

        DefaultUser user1= userRepository.save(new DefaultUser("login","password","name","surname","email",alergensOfUser));
        DefaultUser user2= userRepository.save(new DefaultUser("login","password","name","surname","email",new ArrayList<>()));

        Product product1= productRepository.save(new Product("produkt1",10L,10L,10L,10L,alergensForProduct1));
        Product product2= productRepository.save(new Product("produkt2",10L,10L,10L,10L,new ArrayList<>()));
        Product product3= productRepository.save(new Product("produkt3",10L,10L,10L,10L,alergensForProduct3));
        List<Long> productsForRecipe1= new ArrayList<>();
        productsForRecipe1.add(product2.getId());
        List<Long> productsForRecipe2= new ArrayList<>();
        productsForRecipe2.add(product3.getId());
        List<Long> productsForRecipe3= new ArrayList<>();
        productsForRecipe3.add(product2.getId());
        productsForRecipe3.add(product1.getId());

        Recipe recipe1= recipeService.addRecipe(new RecipeDTO("przepis1","desc",productsForRecipe1));
        Recipe recipe2= recipeService.addRecipe(new RecipeDTO("przepis2","desc",productsForRecipe2));
        Recipe recipe3= recipeService.addRecipe(new RecipeDTO("przepis3","desc",productsForRecipe3));

        RecipeFilterDTO dto1= new RecipeFilterDTO(user1.getUserId());
        RecipeFilterDTO dto2= new RecipeFilterDTO(user2.getUserId());

        //when
        List<Recipe> result1 = recipeFilterService.filterRecipes(dto1);
        List<Recipe> result2 = recipeFilterService.filterRecipes(dto2);
        //then
        Assertions.assertEquals(1,result1.size());
        Assertions.assertEquals(3,result2.size());

    }

    @Test
    void filterRecipes_should_throw_UserNotExistException(){
        //given
        RecipeFilterDTO recipeFilterDTO= new RecipeFilterDTO(1L);
        //when
        Exception thrown=Assertions.assertThrows(UserNotExistException.class,()->{
            recipeFilterService.filterRecipes(recipeFilterDTO);
        });
        //then
        Assertions.assertEquals("Uzytkownik o tym id nie istnieje",thrown.getMessage());
    }
}