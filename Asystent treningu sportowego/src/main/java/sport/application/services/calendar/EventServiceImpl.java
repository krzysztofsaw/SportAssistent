package sport.application.services.calendar;


import sport.application.entities.Event;
import sport.application.entities.User;
import sport.application.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> findAllByUser(User user) {
        return eventRepository.findAllByUser(user);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void delete(Event event) {
        eventRepository.delete(event);
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Event editById(Long id, String title, String start, String end) {
        Event event = eventRepository.findById(id);
        event.setStart(start);
        event.setEnd(end);
        event.setTitle(title);
        event.setDescription(event.getDescription());
        return eventRepository.save(event);
    }


    @Override
    public Event editEventAndColour(Event event, String title, String description, String backgroundColour, String borderColour) {
        event.setTitle(title);
        event.setDescription(description);
        event.setBackgroundColor(backgroundColour);
        event.setBorderColor(borderColour);
        return eventRepository.save(event);
    }
}
