package sport.application.services.calendar;


import sport.application.entities.Event;
import sport.application.entities.User;

import java.util.List;


public interface EventService {
    List<Event> findAllByUser(User user);
    Event save(Event event);
    void delete(Event event);
    Event findById(Long id);
    Event editById(Long id, String title, String start, String end);
    Event editEventAndColour(Event event, String title, String description, String backgroundColour, String borderColour);
}
