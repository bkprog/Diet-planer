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

        Product total=products.stream().reduce(new Product(),(subtotal,element)->{
           subtotal.setCarbs(subtotal.getCarbs()+element.getCarbs());
           subtotal.setKcal(subtotal.getKcal()+element.getKcal());
           subtotal.setFat(subtotal.getFat()+element.getFat());
           subtotal.setProtein(subtotal.getProtein()+element.getProtein());
           return subtotal;
        });

        return SumMacroFromProductListDTO.of(total);
    }

    private List<Product> reduceRecipeListToCombinedProductList(List<Recipe> recipes){
        Recipe recipe=recipes.stream().reduce(new Recipe(),(subtotal,element)->{
            List<Product> subProductList=subtotal.getProducts();
            subProductList.addAll(element.getProducts());
            subtotal.setProducts(subProductList);
            return subtotal;
        });
        return recipe.getProducts();
    }

    private List<Recipe> reduceDailyConsumptionListToCombinedRecipeList(List<DailyConsumption>dailyConsumptions){
        DailyConsumption dailyConsumption= dailyConsumptions.stream().reduce(new DailyConsumption(),(subtotal,element)->{
            List<Recipe> subRecipeList=subtotal.getRecipes();
            subRecipeList.addAll(element.getRecipes());
            subtotal.setRecipes(subRecipeList);
            return subtotal;
        });
        return dailyConsumption.getRecipes();
    }






    private MonthlyConsumptionDTO sumDailyConsumptionFromOneMonth(YearMonth yearMonth, Long userId){
        LocalDate firstDayOfMonth= yearMonth.atDay(1);
        LocalDate lastDayOfMonth= yearMonth.atEndOfMonth();
        DefaultUser user= userService.findUserById(userId);
        List<DailyConsumption> dailyConsumptionsFromOneMonth= dailyConsumptionRepository.findDailyConsumptionsByDateBetweenAndDefaultUser(firstDayOfMonth,lastDayOfMonth,user);


        List<Recipe> recipes=reduceDailyConsumptionListToCombinedRecipeList(dailyConsumptionsFromOneMonth);
        List<Product> products= reduceRecipeListToCombinedProductList(recipes);
        SumMacroFromProductListDTO sumMacroFromProductListDTO = sumMacroFromProductList(products);

        MonthlyConsumptionDTO monthlyConsumptionDTO= MonthlyConsumptionDTO.of(sumMacroFromProductListDTO);
        monthlyConsumptionDTO.setMonth(yearMonth.getMonth());
        return MonthlyConsumptionDTO.of(sumMacroFromProductListDTO);
    }

    public List<MonthlyConsumptionDTO> getYearlyConsumption(Year year,Long userId){

        List<MonthlyConsumptionDTO> monthlyConsumptionDTOS= new ArrayList<>();

        for(int month=1; month<=12;month++){
             YearMonth yearMonth= year.atMonth(month);
             monthlyConsumptionDTOS.add(sumDailyConsumptionFromOneMonth(yearMonth,userId));
        }
        return monthlyConsumptionDTOS;
    }

}
