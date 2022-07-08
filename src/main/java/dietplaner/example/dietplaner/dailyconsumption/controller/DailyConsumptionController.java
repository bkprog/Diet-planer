package dietplaner.example.dietplaner.dailyconsumption.controller;
import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.dailyconsumption.models.DailyConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.models.FindDailyConsumptionByUserAndDateDTO;
import dietplaner.example.dietplaner.dailyconsumption.repository.DailyConsumptionRepository;
import dietplaner.example.dietplaner.dailyconsumption.service.DailyConsumptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/dailyConsumption")
public class DailyConsumptionController {

    private final DailyConsumptionService dailyConsumptionService;

    private final DailyConsumptionRepository dailyConsumptionRepository;



    @PostMapping("/add")
    public DailyConsumption addDailyConsumption(@RequestBody DailyConsumptionDTO dailyConsumptionDTO){
        return dailyConsumptionService.createDailyConsumption(dailyConsumptionDTO);
    }

   @PostMapping("/findByDateAndUser")
    public DailyConsumption findDailyConsumptionByUserAndDate(@RequestBody FindDailyConsumptionByUserAndDateDTO findDailyConsumptionByUserAndDateDTO){
        return  dailyConsumptionService.findDailyConsumptionByUserAndDate(findDailyConsumptionByUserAndDateDTO.getId(),findDailyConsumptionByUserAndDateDTO.getDate());
    }

    @DeleteMapping("/deleteall")
    public void deleteAllDailyConsumption(){
        dailyConsumptionRepository.deleteAll();
    }

    @GetMapping("/getall")
    public List<DailyConsumption> getAllDailyConsumption(){
        return dailyConsumptionRepository.findAll();
    }

    @PostMapping("/findAllByUser")
    public List<DailyConsumption> findAllDailyConsumptionsByUser(@RequestBody Long id){return dailyConsumptionService.findAllDailyConsumptionsByUserId(id);}

    @PutMapping("/update")
    public DailyConsumption updateDailyConsuption(@RequestBody DailyConsumptionDTO dailyConsumptionDTO){
        return dailyConsumptionService.updateDailyConsumption(dailyConsumptionDTO);
    }

}
