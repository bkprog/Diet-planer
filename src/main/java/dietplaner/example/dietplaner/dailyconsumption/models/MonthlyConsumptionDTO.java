package dietplaner.example.dietplaner.dailyconsumption.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Month;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MonthlyConsumptionDTO {
    private Month month;
    private Long kcal;
    private Long protein;
    private Long carbs;
    private Long fat;
}
