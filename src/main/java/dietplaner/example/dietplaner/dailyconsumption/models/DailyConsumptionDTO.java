package dietplaner.example.dietplaner.dailyconsumption.models;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyy-MM-dd")
    private LocalDate date;
    private List<Recipe> recipeList;
    private Long userId;

    //https://stackoverflow.com/questions/28802544/java-8-localdate-jackson-format

}
