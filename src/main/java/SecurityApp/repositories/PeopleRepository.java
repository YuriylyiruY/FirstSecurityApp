package SecurityApp.repositories;

import SecurityApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PeopleRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String username);
}
