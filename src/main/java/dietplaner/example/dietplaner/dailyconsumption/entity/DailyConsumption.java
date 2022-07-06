package dietplaner.example.dietplaner.dailyconsumption.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import dietplaner.example.dietplaner.dailyconsumption.models.DailyConsumptionDTO;
import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class DailyConsumption {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyConsumptionId;

    @Column
    private Date date;

    @ManyToMany
    @JoinTable(
            name="dailyConsumptionToRecipe",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name="dailyConsumptionId")
    )
    private List<Recipe> recipes;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    DefaultUser defaultUser;

    public static DailyConsumption of(DailyConsumptionDTO dailyConsumptionDTO){
        DailyConsumption daily =new DailyConsumption();

        daily.setDate(dailyConsumptionDTO.getDate());
        daily.setDefaultUser(dailyConsumptionDTO.getDefaultUser());
        daily.setRecipes(dailyConsumptionDTO.getRecipeList());

        return daily;
    }

}
/*
https://careydevelopment.us/blog/spring-boot-and-jackson-how-to-get-around-that-infinite-recursion-or-stackoverflow-problem*/
