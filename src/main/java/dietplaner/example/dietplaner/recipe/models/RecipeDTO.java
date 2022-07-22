package dietplaner.example.dietplaner.recipe.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dietplaner.example.dietplaner.product.entity.Product;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "name"
)
public class RecipeDTO {

    private String name;
    private String description;
    private List<Product> products;
}
