package dietplaner.example.dietplaner.dailyconsumption.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MonthlyConsumptionDTO {
    private Date startDate;
    private Date endDate;
    private Long userId;
}
