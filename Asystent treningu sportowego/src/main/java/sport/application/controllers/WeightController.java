package sport.application.controllers;

import sport.application.entities.User;
import sport.application.entities.Weight;
import sport.application.repositories.WeightRepository;
import sport.application.services.UserService;
import sport.application.services.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@ControllerAdvice
public class WeightController {

    @Autowired
    private UserService userService;
    @Autowired
    private WeightService weightService;
    @Autowired
    private WeightRepository weightRepository;



    @GetMapping("/weight")
    public String showIndexPage(Model model, Principal principal) {

        model.addAttribute("havawaight", false);
        String useremail = principal.getName();
        User user = userService.findOne(useremail);
        model.addAttribute("userweight", weightService.findUserWeight(user));
        model.addAttribute("newWeight", new Weight());
        List<Weight> listawagi = weightService.findUserWeight(user);

        if(listawagi.size()!=0){
            model.addAttribute("havawaight", true);
        }

        Map<String, Integer> surveyMap = new LinkedHashMap<>();
        for (Weight waga : listawagi) {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String date = dt.format(waga.getCurrentdate());
            surveyMap.put(date,waga.getWaga());
        }
        model.addAttribute("surveyMap", surveyMap);
        return "views/weight";

    }

    @PostMapping("/weight")
    public String addDimensions(@Valid Weight weight, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {
            return "views/weight";
        }
        String emial = principal.getName();
        User user = userService.findOne(emial);
        Date date = new Date();
        weight.setCurrentdate(date);
        weightService.addWeight(weight, user);
        return  "redirect:/weight";
    }

    @GetMapping("/deleteweight")
    public String deleteWeight(Long id ) {
        weightRepository.delete(id);
        return "redirect:/weight";
    }

}
