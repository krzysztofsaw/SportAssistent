package sport.application.controllers;


import sport.application.entities.Event;
import sport.application.entities.User;
import sport.application.services.UserService;
import sport.application.services.calendar.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by Oan on 18/01/2018.
 * @author Oan Stultjens
 * Controller for the Calendar
 */

@Controller
public class CalendarController {
    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @GetMapping("/calendar")
    public String calendar(HttpServletRequest req, Model model, Authentication authentication, Principal principal) {

        String email = principal.getName();
        User user = userService.findOne(email);

        User userLogged = userService.findOne(email);
        if (userLogged != null) {
            model.addAttribute("loggedUser", user);
            model.addAttribute("event", new Event());
        }
        return "views/calendar";
    }

    @GetMapping("/calendar-addevent")
    public String calendarAddEvent(Authentication authentication, @RequestParam String start, @RequestParam String end, @RequestParam String title, @RequestParam String description, @RequestParam String colour,Principal principal) {
        String email = principal.getName();
        User user = userService.findOne(email);

        if (user != null) {
            if (title.length() > 0) {
                eventService.save(new Event(title, description, start, end, user, colour, colour, true));
                return "redirect:/calendar";
            } else {
                return "redirect:/calendar?error";
            }
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/calendar-delete")
    public String deleteEvent(@RequestParam Long id,Principal principal) {


        String email = principal.getName();
        User user = userService.findOne(email);
        eventService.delete(eventService.findById(id));
        return "redirect:/calendar";
    }

    @GetMapping("/calendar-update")
    public String updateEvent(@RequestParam String title, @RequestParam String start, @RequestParam String end, @RequestParam Long id ,Principal principal) {

        String email = principal.getName();
        User user = userService.findOne(email);
        eventService.editById(id, title, start, end);
        return "redirect:/calendar";
    }

    @GetMapping("/calendar-updateevent")
    public String updateEventNew(@RequestParam String title, @RequestParam String start, @RequestParam String end, @RequestParam Long id, @RequestParam String colour, @RequestParam String desc,Principal principal) {
        String email = principal.getName();
        User user = userService.findOne(email);
        Event event = eventService.findById(id);
        if (title.length() > 0) {
            eventService.editEventAndColour(event, title, desc, colour,  colour);
        } else {
            eventService.editEventAndColour(event, "Event", desc, colour,  colour);
        }
        return "redirect:/calendar";
    }
}
