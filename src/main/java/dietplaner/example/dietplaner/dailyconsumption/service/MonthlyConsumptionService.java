package dietplaner.example.dietplaner.dailyconsumption.service;

import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.dailyconsumption.models.MonthlyConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.repository.DailyConsumptionRepository;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MonthlyConsumptionService {

    private final DailyConsumptionRepository dailyConsumptionRepository;
    private final UserService userService;

    public List<DailyConsumption> getMonthlyList(MonthlyConsumptionDTO monthlyConsumptionDTO){
        DefaultUser user= userService.findUserById(monthlyConsumptionDTO.getUserId());

        return dailyConsumptionRepository.findDailyConsumptionsByDateBetweenAndDefaultUser(monthlyConsumptionDTO.getStartDate(),monthlyConsumptionDTO.getEndDate(),user);
    }

}
