package sport.application.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import sport.application.entities.Plan;

public interface PlanRepository  extends JpaRepository<Plan, Long>{


}
