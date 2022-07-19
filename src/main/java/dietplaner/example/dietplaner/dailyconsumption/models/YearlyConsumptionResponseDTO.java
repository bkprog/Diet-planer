package dietplaner.example.dietplaner.dailyconsumption.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class YearlyConsumptionResponseDTO {
    List<MonthlyConsumptionDTO> monthlyConsumptionDTOList;
}
