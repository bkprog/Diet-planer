package dietplaner.example.dietplaner.recipe.entity;

import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.product.entity.Product;
import dietplaner.example.dietplaner.recipe.models.RecipeDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany
    private List<DailyConsumption> dailyConsumptions;

    @ManyToMany
    private List<Product> products;


    public static Recipe of(RecipeDTO recipeDTO){
        Recipe recipe= new Recipe();
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setName(recipeDTO.getName());
        recipe.setProducts(recipeDTO.getProducts());
        return recipe;
    }



}
