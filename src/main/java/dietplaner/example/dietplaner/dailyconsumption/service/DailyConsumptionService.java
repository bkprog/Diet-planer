package dietplaner.example.dietplaner.dailyconsumption.service;

import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.dailyconsumption.exceptions.DailyConsumptionAlreadyExistException;
import dietplaner.example.dietplaner.dailyconsumption.exceptions.DailyConsumptionNotExistException;
import dietplaner.example.dietplaner.dailyconsumption.exceptions.UserDoesNotHaveAnyDailyConsumptionsException;
import dietplaner.example.dietplaner.dailyconsumption.models.DailyConsumptionDTO;
import dietplaner.example.dietplaner.dailyconsumption.repository.DailyConsumptionRepository;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import dietplaner.example.dietplaner.user.repository.UserRepository;
import dietplaner.example.dietplaner.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DailyConsumptionService {

    private final DailyConsumptionRepository dailyConsumptionRepository;

    private final UserRepository userRepository;

    private final UserService userService;



        private void validateWithUserAndDate(DefaultUser defaultUser, Date date) {
            if (dailyConsumptionRepository.findDailyConsumptionByDefaultUserAndDate(defaultUser, date).isPresent()) {
                throw new DailyConsumptionAlreadyExistException();
            }
        }


        public DailyConsumption createDailyConsumption (DailyConsumptionDTO dailyConsumptionDTO){

            DefaultUser user= userService.findUserById(dailyConsumptionDTO.getUserId());

            validateWithUserAndDate(user,dailyConsumptionDTO.getDate());
            DailyConsumption daily= DailyConsumption.of(dailyConsumptionDTO);
            daily.setDefaultUser(user);
            return dailyConsumptionRepository.save(daily);
        }


        public DailyConsumption updateDailyConsumption(DailyConsumptionDTO dailyConsumptionDTO){
            return dailyConsumptionRepository.save(DailyConsumption.of(dailyConsumptionDTO));
        }

        public DailyConsumption findDailyConsumptionByUserAndDate(Long id, Date date){

            return dailyConsumptionRepository.findDailyConsumptionByDefaultUserUserIdAndDate(id,date).stream().findFirst()
                    .orElseThrow(DailyConsumptionNotExistException::new);

        }


        public List<DailyConsumption> findAllDailyConsumptionsByUserId(Long id){

            return dailyConsumptionRepository.findAllByDefaultUserUserId(id).stream().findFirst()
                    .orElseThrow(UserDoesNotHaveAnyDailyConsumptionsException::new);
        }



        }


