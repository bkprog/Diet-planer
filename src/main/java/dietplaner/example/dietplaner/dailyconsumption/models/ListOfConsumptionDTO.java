package dietplaner.example.dietplaner.dailyconsumption.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListOfConsumptionDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;
}
