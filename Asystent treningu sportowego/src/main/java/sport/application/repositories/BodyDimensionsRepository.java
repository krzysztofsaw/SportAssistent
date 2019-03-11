package sport.application.repositories;

import java.util.List;

import sport.application.entities.BodyDimensions;
import sport.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyDimensionsRepository  extends JpaRepository<BodyDimensions, Long>{

    List<BodyDimensions> findByUser(User user);

}
