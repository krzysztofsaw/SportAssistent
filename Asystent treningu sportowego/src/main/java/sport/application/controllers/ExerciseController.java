package sport.application.controllers;

import sport.application.entities.Exercise;
import sport.application.repositories.ExerciseRepository;
import sport.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;


@Controller
public class ExerciseController {

    @Autowired
    private UserService userService;
    @Autowired
    private ExerciseRepository exerciseRepository;


    @GetMapping("/exercise")
    public String showExercisePage(Model model,@RequestParam(defaultValue = "0") int page) {

//        model.addAttribute("exericise", exerciseRepository.findAll());
        model.addAttribute("data", exerciseRepository.findAll(new PageRequest(page, 8)));
        model.addAttribute("currentPage", page);

        List<Exercise> exercises = exerciseRepository.findAll();

        return "views/exercise";

    }

    @GetMapping("/execisedetails")
    @ResponseBody
    public Exercise fsindOne(Long id , Model model, HttpSession session) {
        Exercise exercise = exerciseRepository.findOne(id);
        session.setAttribute("currnetgoals", exercise.getId());
        return exercise;
    }

}

