package dietplaner.example.dietplaner.dailyconsumption.controller;

import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.dailyconsumption.models.ListOfConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.service.ListOfConsumptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class MonthlyConsumptionController {

    private final ListOfConsumptionService listOfConsumptionService;

    @GetMapping("/api/MonthlyConsumption/findByDateAndUser")
    public List<DailyConsumption> findByDateAndUser(@RequestBody ListOfConsumptionDTO listOfConsumptionDTO){
        return listOfConsumptionService.getListOfDailyConsumptionByAmountOfTimeAndUser(listOfConsumptionDTO);
    }
}
