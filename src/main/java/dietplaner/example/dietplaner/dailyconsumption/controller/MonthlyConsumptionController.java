package dietplaner.example.dietplaner.dailyconsumption.controller;

import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.dailyconsumption.models.MonthlyConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.service.MonthlyConsumptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class MonthlyConsumptionController {

    private final MonthlyConsumptionService monthlyConsumptionService;

    @PostMapping("/api/MonthlyConsumption/findByDateAndUser")
    public List<DailyConsumption> findByDateAndUser(@RequestBody MonthlyConsumptionDTO monthlyConsumptionDTO){
        return monthlyConsumptionService.getMonthlyList(monthlyConsumptionDTO);
    }
}
