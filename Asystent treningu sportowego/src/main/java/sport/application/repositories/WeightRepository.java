package sport.application.repositories;

import java.util.List;

import sport.application.entities.User;
import sport.application.entities.Weight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightRepository  extends JpaRepository<Weight, Long>{

    List<Weight> findByUser(User user);

}
