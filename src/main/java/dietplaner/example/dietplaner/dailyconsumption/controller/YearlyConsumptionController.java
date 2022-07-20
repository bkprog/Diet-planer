package dietplaner.example.dietplaner.dailyconsumption.controller;

import dietplaner.example.dietplaner.dailyconsumption.models.YearlyConsumption.MonthlyConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.models.YearlyConsumption.YearlyConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.service.YearlyConsumptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/yearlyConsumption")
public class YearlyConsumptionController {

    private final YearlyConsumptionService yearlyConsumptionService;

    @GetMapping("/getYearlyConsumption")
    public List<MonthlyConsumptionDTO> getYearlyConsumption(@RequestBody YearlyConsumptionDTO yearlyConsumptionDTO){
        return yearlyConsumptionService.getYearlyConsumption(yearlyConsumptionDTO.getYear(),yearlyConsumptionDTO.getUserId());
    }

}
