package sport.application.controllers;

import sport.application.entities.Goals;
import sport.application.entities.User;
import sport.application.repositories.GoalsRepository;
import sport.application.services.GoalsService;
import sport.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
public class GoalsController {

    @Autowired
    private UserService userService;
    @Autowired
    private GoalsService goalsService;
    @Autowired
    private GoalsRepository goalsRepository;


    @GetMapping("/goals")
    public String showGoalsPage( Model model, Principal principal,HttpSession session) {



        model.addAttribute("havagoal", false);

        String emial = principal.getName();
        User user = userService.findOne(emial);
        List<String> typaktywnosci = new ArrayList<>(Arrays.asList( "Ogólna","Silownia", "Siatkówka", "Koszykówka", "Bieganie", "Rolki"));
        List<String> typcelu = new ArrayList<>(Arrays.asList("Godziny", "Dystans", "Kalorie","Aktywnosci"));

        model.addAttribute("typaktywnosci", typaktywnosci);
        model.addAttribute("typcelu", typcelu);
        model.addAttribute("usergoals", goalsService.findUserGoals(user));
        model.addAttribute("goalsss", new Goals());
        List<Goals> usergoals = goalsService.findUserGoals(user);

        if(usergoals.size()!=0){
            model.addAttribute("havagoal", true);
        }
//        System.out.println("sprawdz  " + usergoals.get(0).getId());
//        model.addAttribute("showtable", goalsService);


        return "views/goals";

    }

    @PostMapping("/goals")
    public String showGoalsPage(@Valid Goals goals ,Model model,  Principal principal) {
//        if(bindingResult.hasErrors()) {
//            return "views/goals";
//        }
        String emial = principal.getName();
        User user = userService.findOne(emial);
        goals.setCurentresult(0);
        model.addAttribute("goalsss", new Goals());
        List<String> typaktywnosci = new ArrayList<>(Arrays.asList("Siłownia", "Siatkówka", "Koszykówka", "Bieganie", "Rolki"));
        List<String> typcelu = new ArrayList<>(Arrays.asList("Godziny", "Dystans", "Kalorie","Aktywności"));
        model.addAttribute("typaktywnosci", typaktywnosci);
        model.addAttribute("typcelu", typcelu);
        model.addAttribute("usergoals", goalsService.findUserGoals(user));
        goalsService.addGoals(goals, user);
        System.out.println("Curent result to "+ goals.getGoal());
        System.out.println("Curent result to "+ goals.getGoal());


        return "redirect:/goals";
    }



    @PostMapping("/addcurent")
    public String addcurent( HttpSession session, Integer curentresult) {
        Long aidik = (Long) session.getAttribute("currnetgoals");
        Goals currnetgoals = goalsService.findOne(aidik);
        System.out.println("idyfikoator przechywconego  urzytownika "+ currnetgoals.getId());
        currnetgoals.setId(aidik);
        currnetgoals.setUser(currnetgoals.getUser());
        currnetgoals.setStartgoals(currnetgoals.getStartgoals());
        currnetgoals.setStartgoals(currnetgoals.getStopgoals());
        currnetgoals.setCurentresult(curentresult);
        goalsService.save(currnetgoals);
        return "redirect:/goals";
    }

    @GetMapping("/addcurent")
    @ResponseBody
    public Goals addcurent(Long id ,HttpSession session) {
        Goals currnetgoals = goalsService.findOne(id);
        session.setAttribute("currnetgoals", currnetgoals.getId());
        System.out.println("idyfikoator nadanego  urzytkownika "+ currnetgoals.getId());

        return currnetgoals;
    }


    @PostMapping("/edit")
    public String save(HttpSession session, String nazwa, String typeactivity, String typegoals, Integer goal) {

        Long aidik = (Long) session.getAttribute("currnetgoals");
        Goals cg = goalsService.findOne(aidik);
        cg.setId(cg.getId());
        cg.setTypegoals(typegoals);
        cg.setGoal(goal);
        cg.setTypeactivity(typeactivity);
        cg.setUser(cg.getUser());
        cg.setNazwa(nazwa);
        cg.getCurentresult();
        goalsService.save(cg);
        return "redirect:/goals";
    }



    @GetMapping("/edit")
    @ResponseBody
    public Goals findOne(Long id ,HttpSession session) {
        Goals currnetgoals = goalsRepository.findOne(id);
        session.setAttribute("currnetgoals", currnetgoals.getId());
        return currnetgoals;
    }



    @GetMapping("/delete")
    public String delate(Long id) {
        goalsService.deltegoals(id);
        return "redirect:/goals";
    }
}
