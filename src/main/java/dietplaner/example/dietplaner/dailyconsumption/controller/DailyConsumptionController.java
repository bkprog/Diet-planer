package dietplaner.example.dietplaner.dailyconsumption.controller;
import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.dailyconsumption.models.DailyConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.repository.DailyConsumptionRepository;
import dietplaner.example.dietplaner.dailyconsumption.service.DailyConsumptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dailyConsumption")
public class DailyConsumptionController {

    private final DailyConsumptionService dailyConsumptionService;

    private final DailyConsumptionRepository dailyConsumptionRepository;

    @PostMapping("/add")
    public DailyConsumption addDailyConsumption(@RequestBody DailyConsumptionDTO dailyConsumptionDTO){
        return dailyConsumptionService.createDailyConsumption(dailyConsumptionDTO);
    }

    @DeleteMapping("/deleteall")
    public void deleteAllDailyConsumption(){
        dailyConsumptionRepository.deleteAll();
    }

    @GetMapping("/getall")
    public List<DailyConsumption> getAllDailyConsumption(){
        return dailyConsumptionRepository.findAll();
    }

    @PutMapping("/update")
    public DailyConsumption updateDailyConsuption(@RequestBody DailyConsumptionDTO dailyConsumptionDTO){
        return dailyConsumptionService.updateDailyConsumption(dailyConsumptionDTO);
    }

}
