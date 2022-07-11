package dietplaner.example.dietplaner.dailyconsumption.repository;

import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyConsumptionRepository extends JpaRepository<DailyConsumption,Long> {

    Optional<DailyConsumption> findDailyConsumptionByDefaultUserAndDate(DefaultUser defaultUser, Date date);


    Optional<DailyConsumption> findDailyConsumptionByDefaultUserUserIdAndDate(Long id,Date date);


    Optional<List<DailyConsumption>>findAllByDefaultUserUserId(Long id);

    List<DailyConsumption> findDailyConsumptionsByDateBetweenAndDefaultUser(Date startDate, Date endDate,DefaultUser defaultUser);




}
