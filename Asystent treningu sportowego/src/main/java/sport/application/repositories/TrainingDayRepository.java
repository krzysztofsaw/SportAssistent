package sport.application.repositories;

import sport.application.entities.TrainingDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingDayRepository  extends JpaRepository<TrainingDay, Long>{


//    List<TrainingDay> findAllByTraingid(Long Id);

}
