package sport.application.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import sport.application.entities.Event;
import sport.application.entities.User;
import sport.application.services.UserService;
import sport.application.services.calendar.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

/**
 * Created by Oan on 26/01/2018.
 * @author Oan Stultjens
 * RestController for the Events, also maps the To-Do (task)'s to the calendar
 */

@RestController
@RequestMapping("/api/event")
public class RestEventController {
    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;



    @GetMapping("/all")
    public String getEvents(Authentication authentication, Principal principal) {
        String jsonMessage = null;
        try {
                String email = principal.getName();
                User user = userService.findOne(email);
                List<Event> events = eventService.findAllByUser(user);
//                List<Task> tasks = taskService.findByUserAndCompletedIsFalseAndApprovedIsTrue(userLogged);
//                if (userLogged.isTodoToCalendar()) {
////                    for (Task task : tasks) {
////                        events.add(new Event("To-do: "+task.getDescription(), "Task from: " + task.getCreator().getFirstName() + " " + task.getCreator().getLastName(), task.getTargetDate().toString(), task.getTargetDate().toString(), userLogged, CustomAppSettings.EVENT_TODO_COLOUR, CustomAppSettings.EVENT_TODO_COLOUR, false));
////                    }
//                }
                ObjectMapper mapper = new ObjectMapper();
                jsonMessage = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);
//
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return jsonMessage;
    }
}
