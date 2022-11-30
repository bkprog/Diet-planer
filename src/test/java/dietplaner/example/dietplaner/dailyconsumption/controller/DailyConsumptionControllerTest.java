package dietplaner.example.dietplaner.dailyconsumption.controller;

import dietplaner.example.dietplaner.dailyconsumption.models.DailyConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.repository.DailyConsumptionRepository;
import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.product.repository.ProductRepository;
import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.recipe.repository.RecipeRepository;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.repository.UserRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DailyConsumptionControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;
    @Autowired
    DailyConsumptionRepository dailyConsumptionRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    public void tearDown(){
        dailyConsumptionRepository.deleteAll();
        userRepository.deleteAll();
        recipeRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void return_200_ok_http_status_addDailyConsumption() throws Exception{
        DefaultUser user= userRepository.save(new DefaultUser("Login1","password1","name1","surname1","email1"));
        LocalDate date= LocalDate.of(2022,11,14);

        Product product = productRepository.save(new Product("produkt",10L,10L,10L,10L));
        List<Product> productsForRecipe= new ArrayList<>();
        productsForRecipe.add(product);

        Recipe recipe= recipeRepository.save(new Recipe("name","desc",productsForRecipe));
        List<Long> recipesForDailyConsumption= new ArrayList<>();
        recipesForDailyConsumption.add(recipe.getRecipeId());

        DailyConsumptionDTO dto= new DailyConsumptionDTO(date,recipesForDailyConsumption,user.getUserId());

        String jsonBody = new JSONObject()
                .put("date", dto.getDate())
                .put("recipes",dto.getRecipes())
                .put("userId",dto.getUserId())
                .toString();

        mockMvc.perform(post("/api/dailyConsumption/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}