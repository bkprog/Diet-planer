package dietplaner.example.dietplaner.dailyconsumption.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindDailyConsumptionByUserAndDateDTO {

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private LocalDate date;
    private Long id;
}
