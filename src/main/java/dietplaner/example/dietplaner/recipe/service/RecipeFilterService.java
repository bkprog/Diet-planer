package dietplaner.example.dietplaner.recipe.service;

import dietplaner.example.dietplaner.alergen.entity.Alergen;
import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.recipe.models.RecipeFilterDTO;
import dietplaner.example.dietplaner.recipe.repository.RecipeRepository;
import dietplaner.example.dietplaner.user.exceptions.UserNotExistException;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeFilterService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;




    private boolean containsAlergen(Product product,List<Alergen> alergensOfUser) {
        List<Alergen> alergenList = product.getAlergens();

        return alergenList.stream()
                .anyMatch(alergen -> alergensOfUser.contains(alergen));
    }


   private boolean isNotMadeOfAlergen(Recipe recipe,List<Alergen> alergensOfUser){
       List<Product> productsList=recipe.getProducts();
       return productsList.stream().noneMatch(product -> containsAlergen(product,alergensOfUser));
   }


    @Transactional
    public List<Recipe> filterRecipes(RecipeFilterDTO recipeFilterDTO){

       DefaultUser user= userRepository.findDefaultUserByUserId(recipeFilterDTO.getUserId()).stream().findFirst().orElseThrow(UserNotExistException::new);
       List<Alergen> alergensOfUser= user.getAlergens();
       List<Recipe> recipes= recipeRepository.findAll();

       return recipes.stream().
               filter(recipe -> isNotMadeOfAlergen(recipe,alergensOfUser))
               .collect(Collectors.toList());


   }

}
