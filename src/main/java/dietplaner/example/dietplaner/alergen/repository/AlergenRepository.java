package dietplaner.example.dietplaner.alergen.repository;

import dietplaner.example.dietplaner.alergen.entity.Alergen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AlergenRepository extends JpaRepository<Alergen,Long> {

    Optional<Alergen> getAlergenByAlergenId(Long alergenId);

    Optional<Alergen> getAlergenByName(String alergenName);
}
