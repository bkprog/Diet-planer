package dietplaner.example.dietplaner.dailyconsumption.repository;

import dietplaner.example.dietplaner.dailyconsumption.entity.DailyConsumption;
import dietplaner.example.dietplaner.user.entity.DefaultUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyConsumptionRepository extends JpaRepository<DailyConsumption,Long> {

    Optional<DailyConsumption> findDailyConsumptionByDefaultUserAndDate(DefaultUser defaultUser, LocalDate date);


    Optional<DailyConsumption> findDailyConsumptionByDefaultUserUserIdAndDate(Long id,LocalDate date);


    Optional<List<DailyConsumption>>findAllByDefaultUserUserId(Long id);

    List<DailyConsumption> findDailyConsumptionsByDateBetweenAndDefaultUser(LocalDate startDate, LocalDate endDate, DefaultUser defaultUser);




}
