package sport.application.repositories;

import sport.application.entities.Event;
import sport.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByUser(User user);
    Event findById(Long id);
}
