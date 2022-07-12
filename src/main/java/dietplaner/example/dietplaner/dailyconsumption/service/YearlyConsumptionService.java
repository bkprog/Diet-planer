package dietplaner.example.dietplaner.dailyconsumption.service;

import dietplaner.example.dietplaner.dailyconsumption.repository.DailyConsumptionRepository;
import dietplaner.example.dietplaner.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class YearlyConsumptionService extends ListOfConsumptionService {

    private final UserService userService;
    private final DailyConsumptionRepository dailyConsumptionRepository;





}
