package dietplaner.example.dietplaner.recipe.models;

import dietplaner.example.dietplaner.product.entity.Product;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RecipeDTO {

    private String name;
    private String description;
    private List<Product> products;
}
