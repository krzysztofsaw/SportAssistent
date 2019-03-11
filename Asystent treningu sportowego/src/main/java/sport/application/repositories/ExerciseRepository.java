package sport.application.repositories;

import sport.application.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository  extends JpaRepository<Exercise, Long>{



}
