package dietplaner.example.dietplaner.dailyconsumption.models.YearlyConsumption;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class YearlyConsumptionDTO {

    @JsonFormat(pattern = "yyyy")
    private Year year;
    private Long userId;
}
