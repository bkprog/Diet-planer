package dietplaner.example.dietplaner.dailyconsumption.service;

import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.dailyconsumption.exceptions.DailyConsumptionAlreadyExistException;
import dietplaner.example.dietplaner.dailyconsumption.exceptions.DailyConsumptionNotExistException;
import dietplaner.example.dietplaner.dailyconsumption.exceptions.UserDoesNotHaveAnyDailyConsumptionsException;
import dietplaner.example.dietplaner.dailyconsumption.models.DailyConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.models.UpdateDailyConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.repository.DailyConsumptionRepository;
import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.recipe.service.RecipeService;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DailyConsumptionService {

    private final DailyConsumptionRepository dailyConsumptionRepository;

    private final UserService userService;
    private final RecipeService recipeService;



        private void validateWithUserAndDate(DefaultUser defaultUser, LocalDate date) {
            if (dailyConsumptionRepository.findDailyConsumptionByDefaultUserAndDate(defaultUser, date).isPresent()) {
                throw new DailyConsumptionAlreadyExistException();
            }
        }

        private List<Recipe> findRecipesListFromRecipesIdList(List<Long> recipeIdList){
            List<Recipe> recipes= new ArrayList<>();

            recipeIdList.stream().forEach(recipeId->{
                recipeService.validateIfRecipeExistById(recipeId);
                Recipe recipe= recipeService.getRecipeById(recipeId);
                recipes.add(recipe);
            });
            return recipes;
        }

        public DailyConsumption createDailyConsumption (DailyConsumptionDTO dailyConsumptionDTO){
            List<Recipe>recipes=findRecipesListFromRecipesIdList(dailyConsumptionDTO.getRecipes());
            DefaultUser user= userService.findUserById(dailyConsumptionDTO.getUserId());

            validateWithUserAndDate(user,dailyConsumptionDTO.getDate());

            DailyConsumption daily= DailyConsumption.of(dailyConsumptionDTO);
            daily.setDefaultUser(user);
            daily.setRecipes(recipes);
            return dailyConsumptionRepository.save(daily);
        }


        public DailyConsumption addRecipeTODailyConsumption(UpdateDailyConsumptionDTO updateDailyConsumptionDTO){
            Recipe recipe=recipeService.getRecipeById(updateDailyConsumptionDTO.getRecipeId());
            DailyConsumption daily= findDailyConsumptionByUserAndDate(updateDailyConsumptionDTO.getUserId(),updateDailyConsumptionDTO.getDate());
            List<Recipe> recipesFromDaily=daily.getRecipes();
            recipesFromDaily.add(recipe);
            daily.setRecipes(recipesFromDaily);
            return dailyConsumptionRepository.save(daily);
        }

        public DailyConsumption findDailyConsumptionByUserAndDate(Long userId, LocalDate date){

            return dailyConsumptionRepository.findDailyConsumptionByDefaultUserUserIdAndDate(userId,date).stream().findFirst()
                    .orElseThrow(DailyConsumptionNotExistException::new);

        }


        public List<DailyConsumption> findAllDailyConsumptionsByUserId(Long id){

            return dailyConsumptionRepository.findAllByDefaultUserUserId(id).stream().findFirst()
                    .orElseThrow(UserDoesNotHaveAnyDailyConsumptionsException::new);
        }





        }


