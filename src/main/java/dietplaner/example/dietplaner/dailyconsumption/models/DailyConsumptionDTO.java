package dietplaner.example.dietplaner.dailyconsumption.models;

import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DailyConsumptionDTO {

    private Date date;
    private List<Recipe> recipeList;
    private DefaultUser defaultUser;
}
