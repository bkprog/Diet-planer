package dietplaner.example.dietplaner.dailyconsumption.models;

import dietplaner.example.dietplaner.recipe.entity.Recipe;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DailyConsumptionDTO {

    private LocalDate date;
    private List<Recipe> recipeList;
    private Long userId;

}
