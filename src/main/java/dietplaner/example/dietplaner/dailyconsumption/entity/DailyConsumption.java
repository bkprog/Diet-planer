package dietplaner.example.dietplaner.dailyconsumption.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dietplaner.example.dietplaner.dailyconsumption.models.DailyConsumptionDTO;
import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "dailyConsumptionId"
)*/
public class DailyConsumption {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyConsumptionId;

    @Column
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private LocalDate date;

    @JsonManagedReference(value = "dailyToRecipe")
    @ManyToMany
    @JoinTable(
            name="dailyConsumptionToRecipe",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name="dailyConsumptionId")
    )
    private List<Recipe> recipes;

    @JsonBackReference(value = "DailyToUser")
    @ManyToOne
    @JoinColumn(name = "user_id")
    DefaultUser defaultUser;




    public static DailyConsumption of(DailyConsumptionDTO dailyConsumptionDTO){

        DailyConsumption daily =new DailyConsumption();

        daily.setDate(dailyConsumptionDTO.getDate());
        daily.setRecipes(dailyConsumptionDTO.getRecipeList());

        return daily;
    }

}

