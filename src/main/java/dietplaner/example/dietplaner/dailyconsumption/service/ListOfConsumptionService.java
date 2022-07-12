package dietplaner.example.dietplaner.dailyconsumption.service;

import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.dailyconsumption.models.ListOfConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.repository.DailyConsumptionRepository;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@NoArgsConstructor
public class ListOfConsumptionService {



    private DailyConsumptionRepository dailyConsumptionRepository;
    private  UserService userService;

    @Autowired
    public ListOfConsumptionService(DailyConsumptionRepository dailyConsumptionRepository,UserService userService){
        this.dailyConsumptionRepository=dailyConsumptionRepository;
        this.userService=userService;
    }



    public List<DailyConsumption> getListOfDailyConsumptionByAmountOfTimeAndUser(ListOfConsumptionDTO monthlyConsumptionDTO){
        DefaultUser user= userService.findUserById(monthlyConsumptionDTO.getUserId());

        return dailyConsumptionRepository.findDailyConsumptionsByDateBetweenAndDefaultUser(monthlyConsumptionDTO.getStartDate(),monthlyConsumptionDTO.getEndDate(),user);
    }

}
