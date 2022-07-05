package dietplaner.example.dietplaner.dailyconsumption.service;

import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.dailyconsumption.exceptions.DailyConsumptionAlreadyExistException;
import dietplaner.example.dietplaner.dailyconsumption.models.DailyConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.repository.DailyConsumptionRepository;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class DailyConsumptionService {

    private final DailyConsumptionRepository dailyConsumptionRepository;

    private void validateWithUserAndDate(DefaultUser defaultUser, Date date) {
        if (dailyConsumptionRepository.findDailyConsumptionByDefaultUserAndDate(defaultUser, date).isPresent()) {
            throw new DailyConsumptionAlreadyExistException();
        }
    }

        public DailyConsumption createDailyConsumption (DailyConsumptionDTO dailyConsumptionDTO){
                validateWithUserAndDate(dailyConsumptionDTO.getDefaultUser(),dailyConsumptionDTO.getDate());
            return dailyConsumptionRepository.save(DailyConsumption.of(dailyConsumptionDTO));
        }


        public DailyConsumption updateDailyConsumption(DailyConsumptionDTO dailyConsumptionDTO){
            return dailyConsumptionRepository.save(DailyConsumption.of(dailyConsumptionDTO));
        }
    }
