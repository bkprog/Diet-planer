package dietplaner.example.dietplaner.recipe.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "name"
)*/
public class RecipeDTO {

    private String name;
    private String description;
    private List<Long>products;
}
