package dietplaner.example.dietplaner.dailyconsumption.service;

import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.dailyconsumption.models.MonthlyConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.repository.DailyConsumptionRepository;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@AllArgsConstructor
public class YearlyConsumptionService extends ListOfConsumptionService {

    private final UserService userService;
    private final DailyConsumptionRepository dailyConsumptionRepository;





    private MonthlyConsumptionDTO sumDailyConsumptionFromOneMonth(YearMonth yearMonth, Long userId){
        LocalDate firstDayOfMonth= yearMonth.atDay(1);
        LocalDate lastDayOfMonth= yearMonth.atEndOfMonth();
        DefaultUser user= userService.findUserById(userId);
        List<DailyConsumption> dailyConsumptionsFromOneMonth= dailyConsumptionRepository.findDailyConsumptionsByDateBetweenAndDefaultUser(firstDayOfMonth,lastDayOfMonth,user);
        MonthlyConsumptionDTO monthlyConsumptionDTO= new MonthlyConsumptionDTO();
        monthlyConsumptionDTO.setMonth(yearMonth.getMonth());

       /* dailyConsumptionsFromOneMonth.stream().reduce(monthlyConsumptionDTO,(subtotal,element)->{
            monthlyConsumptionDTO.setCarbs(monthlyConsumptionDTO.getCarbs()+subtotal.);
        })*/
        return monthlyConsumptionDTO;
    }

}
