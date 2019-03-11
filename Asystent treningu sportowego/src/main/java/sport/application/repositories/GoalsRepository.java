package sport.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import sport.application.entities.Goals;
import sport.application.entities.User;

public interface GoalsRepository  extends JpaRepository<Goals, Long>{

    List<Goals> findByUser(User user);

}
