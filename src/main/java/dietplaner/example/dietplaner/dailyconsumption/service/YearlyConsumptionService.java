package dietplaner.example.dietplaner.dailyconsumption.service;

import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.dailyconsumption.models.YearlyConsumption.MonthlyConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.models.YearlyConsumption.SumMacroFromProductListDTO;
import dietplaner.example.dietplaner.dailyconsumption.repository.DailyConsumptionRepository;
import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class YearlyConsumptionService extends ListOfConsumptionService {

    private final UserService userService;
    private final DailyConsumptionRepository dailyConsumptionRepository;



    private SumMacroFromProductListDTO sumMacroFromProductList(List<Product> products){
        Product startProduct= new Product(0L,0L,0L,0L);

        Product total=products.stream().reduce(startProduct,(subtotal,element)->{
           subtotal.setCarbs(subtotal.getCarbs()+element.getCarbs());
           subtotal.setKcal(subtotal.getKcal()+element.getKcal());
           subtotal.setFat(subtotal.getFat()+element.getFat());
           subtotal.setProtein(subtotal.getProtein()+element.getProtein());
           return subtotal;
        });

        return SumMacroFromProductListDTO.of(total);
    }

    private List<Product> reduceRecipeListToCombinedProductList(List<Recipe> recipes){
        List<Product> products= new ArrayList<>();
        recipes.forEach(recipe -> {
            if(recipe.getProducts()!=null && !recipe.getProducts().isEmpty()){
                products.addAll(recipe.getProducts());
            }
        });
        return products;
    }

    private List<Recipe> reduceDailyConsumptionListToCombinedRecipeList(List<DailyConsumption>dailyConsumptions){


        List<Recipe> recipes= new ArrayList<>();
        dailyConsumptions.forEach(dailyConsumption -> {

            if(dailyConsumption.getRecipes()!=null && !dailyConsumption.getRecipes().isEmpty()){
                recipes.addAll(dailyConsumption.getRecipes());
            }
        });
        return recipes;
    }






    private MonthlyConsumptionDTO sumDailyConsumptionFromOneMonth(YearMonth yearMonth, Long userId){
        LocalDate firstDayOfMonth= yearMonth.atDay(1);
        LocalDate lastDayOfMonth= yearMonth.atEndOfMonth();
        DefaultUser user= userService.findUserById(userId);
        List<DailyConsumption> dailyConsumptionsFromOneMonth= dailyConsumptionRepository.findDailyConsumptionsByDateBetweenAndDefaultUser(firstDayOfMonth,lastDayOfMonth,user);

        if(dailyConsumptionsFromOneMonth!=null){
            List<Recipe> recipes=reduceDailyConsumptionListToCombinedRecipeList(dailyConsumptionsFromOneMonth);
                List<Product> products= reduceRecipeListToCombinedProductList(recipes);
                SumMacroFromProductListDTO sumMacroFromProductListDTO = sumMacroFromProductList(products);
                MonthlyConsumptionDTO monthlyConsumptionDTO= MonthlyConsumptionDTO.of(sumMacroFromProductListDTO);
                monthlyConsumptionDTO.setMonth(yearMonth.getMonth());
                return monthlyConsumptionDTO;
        }
        else{
            MonthlyConsumptionDTO monthlyConsumptionDTO= new MonthlyConsumptionDTO();
            monthlyConsumptionDTO.setMonth(yearMonth.getMonth());
            return monthlyConsumptionDTO;
        }



    }

    public List<MonthlyConsumptionDTO> getYearlyConsumption(Year year,Long userId){

        List<MonthlyConsumptionDTO> monthlyConsumptionDTOS= new ArrayList<>();

        for(int month=1; month<=12;month++){
             YearMonth yearMonth= year.atMonth(month);
             MonthlyConsumptionDTO monthlyConsumptionDTO= sumDailyConsumptionFromOneMonth(yearMonth,userId);
             monthlyConsumptionDTOS.add(monthlyConsumptionDTO);
        }
        return monthlyConsumptionDTOS;
    }

}
