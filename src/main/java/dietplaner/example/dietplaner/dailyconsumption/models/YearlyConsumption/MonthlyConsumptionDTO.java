package dietplaner.example.dietplaner.dailyconsumption.models.YearlyConsumption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Month;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MonthlyConsumptionDTO {
    private Month month;
    private Long kcal;
    private Long protein;
    private Long carbs;
    private Long fat;

    public static MonthlyConsumptionDTO of(SumMacroFromProductListDTO sumMacroFromProductListDTO){
        MonthlyConsumptionDTO monthlyConsumptionDTO= new MonthlyConsumptionDTO();
        monthlyConsumptionDTO.setCarbs(sumMacroFromProductListDTO.getCarbs());
        monthlyConsumptionDTO.setFat(sumMacroFromProductListDTO.getFat());
        monthlyConsumptionDTO.setKcal(sumMacroFromProductListDTO.getKcal());
        monthlyConsumptionDTO.setProtein(sumMacroFromProductListDTO.getProtein());
        return monthlyConsumptionDTO;
    }
}
