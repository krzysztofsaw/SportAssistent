package sport.application.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import sport.application.entities.Training;

public interface TrainingRepository  extends JpaRepository<Training, Long>{



}
