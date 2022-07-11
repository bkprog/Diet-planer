package dietplaner.example.dietplaner.dailyconsumption.service;

import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.dailyconsumption.models.YearlyConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.repository.DailyConsumptionRepository;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class YearlyConsumptionService {

    private final UserService userService;
    private final DailyConsumptionRepository dailyConsumptionRepository;

    private List<DailyConsumption> findAllDailyConsumptionOfOneUser(YearlyConsumptionDTO yearlyConsumptionDTO){
        DefaultUser user= userService.findUserById(yearlyConsumptionDTO.getUserId());
        List<DailyConsumption> allDailyConsumptionsOfOneUser= dailyConsumptionRepository.findDailyConsumptionsByDateBetweenAndDefaultUser(yearlyConsumptionDTO.getStartDate(),yearlyConsumptionDTO.getEndDate(),user);
        return allDailyConsumptionsOfOneUser;
    }

}
