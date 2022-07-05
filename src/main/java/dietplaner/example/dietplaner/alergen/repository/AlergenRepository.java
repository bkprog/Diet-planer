package dietplaner.example.dietplaner.alergen.repository;

import dietplaner.example.dietplaner.alergen.entity.Alergen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlergenRepository extends JpaRepository<Alergen,Long> {
}
