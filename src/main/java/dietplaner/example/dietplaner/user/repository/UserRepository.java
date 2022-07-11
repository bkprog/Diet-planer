package dietplaner.example.dietplaner.user.repository;

import dietplaner.example.dietplaner.user.entity.DefaultUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DefaultUser,Long> {

    Optional<DefaultUser> findDefaultUserByLogin(String login);

    Optional<DefaultUser> findDefaultUserByUserId(Long id);

    Optional<DefaultUser> findDefaultUserByLoginAndPassword(String login, String password);


}
