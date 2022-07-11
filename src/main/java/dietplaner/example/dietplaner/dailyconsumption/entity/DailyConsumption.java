package dietplaner.example.dietplaner.dailyconsumption.entity;


import com.fasterxml.jackson.annotation.*;
import dietplaner.example.dietplaner.dailyconsumption.models.DailyConsumptionDTO;
import dietplaner.example.dietplaner.recipe.entity.Recipe;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.service.UserService;
import lombok.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date date;

    @ManyToMany
    @JoinTable(
            name="dailyConsumptionToRecipe",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name="dailyConsumptionId")
    )
    private List<Recipe> recipes;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    DefaultUser defaultUser;




    public static DailyConsumption of(DailyConsumptionDTO dailyConsumptionDTO){

        DailyConsumption daily =new DailyConsumption();

        daily.setDate(dailyConsumptionDTO.getDate());
        daily.setRecipes(dailyConsumptionDTO.getRecipeList());

        return daily;
    }

}

