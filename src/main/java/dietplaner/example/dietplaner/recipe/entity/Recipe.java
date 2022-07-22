package dietplaner.example.dietplaner.recipe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dietplaner.example.dietplaner.comment.entity.Comment;
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


    @JsonBackReference(value = "RecipeToDaily")
    @ManyToMany
    private List<DailyConsumption> dailyConsumptions;

    @JsonManagedReference(value = "RecipeToProduct")
    @ManyToMany
    private List<Product> products;


    @JsonManagedReference(value = "RecipeToComment")
    @OneToMany(
            mappedBy = "recipe"
    )
    private List<Comment> comments;


    public static Recipe of(RecipeDTO recipeDTO){
        Recipe recipe= new Recipe();
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setName(recipeDTO.getName());
        return recipe;
    }



}
