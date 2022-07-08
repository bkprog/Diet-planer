package dietplaner.example.dietplaner.dailyconsumption.models;

import dietplaner.example.dietplaner.user.entity.DefaultUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindDailyConsumptionByUserAndDateDTO {

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date date;
    private Long id;
}
