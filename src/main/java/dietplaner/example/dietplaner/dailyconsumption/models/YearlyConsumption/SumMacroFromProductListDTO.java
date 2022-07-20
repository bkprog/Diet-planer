package dietplaner.example.dietplaner.dailyconsumption.models.YearlyConsumption;

import dietplaner.example.dietplaner.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SumMacroFromProductListDTO {
    private Long kcal;
    private Long fat;
    private Long protein;
    private Long carbs;

    public static SumMacroFromProductListDTO of(Product product){
        SumMacroFromProductListDTO sumMacroFromProductListDTO = new SumMacroFromProductListDTO();
        sumMacroFromProductListDTO.setCarbs(product.getCarbs());
        sumMacroFromProductListDTO.setFat(product.getFat());
        sumMacroFromProductListDTO.setKcal(product.getKcal());
        sumMacroFromProductListDTO.setProtein(product.getProtein());
        return sumMacroFromProductListDTO;
    }
}
