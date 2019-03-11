package sport.application.controllers;


import sport.application.entities.BodyDiet;
import sport.application.entities.DetailsDiet;
import sport.application.entities.User;
import sport.application.repositories.BodyDietRepository;
import sport.application.repositories.DetailsDietRepository;
import sport.application.repositories.DietRepository;
import sport.application.services.BodyDietService;
import sport.application.services.DietService;
import sport.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sport.application.entities.Diet;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class DietController {

    @Autowired
    private UserService userService;
    @Autowired
    private DietRepository dietRepository;
    @Autowired
    private DetailsDietRepository detailsDietRepository;
    @Autowired
    private BodyDietRepository bodyDietRepository;
    @Autowired
    private BodyDietService bodyDietService;
    @Autowired
    private DietService dietService;


    @GetMapping("/diet")
    public String showDietpage(Model model, Principal principal, HttpSession session, SessionStatus status) {

        String emial = principal.getName();
        User user = userService.findOne(emial);


        List<Integer> liczbaposilkow = new ArrayList<>(Arrays.asList(4, 5));
        model.addAttribute("liczbaposilkow", liczbaposilkow);
        model.addAttribute("diet", new Diet());

        Long iddnia = (Long) session.getAttribute("findday");
        System.out.println("przchwycone idik dnia " + iddnia);


        if (user.isHavadiet()) {
            model.addAttribute("sherediet", false);
            model.addAttribute("checkdiet", true);

            Diet diet = dietRepository.findOne(user.getDietid()); // znaleziony konkretna diete
            List<DetailsDiet> detailsDiets = diet.getDetailsDiets();


//            if (iddnia != null) {
//                System.out.println(detailsDiets.get(0).getDzientygodnia());
//            }


//                model.addAttribute("dzientydoniaaa", detailsDiets.get(0).getDzientygodnia());
//
//            if(iddnia!=null) {
//                model.addAttribute("dzientydoniaaa", detailsDiets.get(Math.toIntExact(8)).getDzientygodnia());
//            }

            if (iddnia == null) {
                DetailsDiet detailsDiet = bodyDietService.findDetailsFromMonday(detailsDiets);
                List<BodyDiet> bodyDiets = detailsDiet.getBodyDiets();
                model.addAttribute("bodyDiets", bodyDiets);
            }
            if (iddnia != null) {
                DetailsDiet detailsDiet = detailsDietRepository.findOne(iddnia);
//                model.addAttribute("dzientygodnia",  detailsDiet.getDzientygodnia());
                List<BodyDiet> bodyDiets = detailsDiet.getBodyDiets();
                model.addAttribute("bodyDiets", bodyDiets);
            }
            model.addAttribute("dietdetails", detailsDiets);
        }

        if (!user.isHavadiet()) {
            //pokaz ankiete
            model.addAttribute("sherediet", true);
            model.addAttribute("checkdiet", false);
        }


//        for (int i = 0; i < bodyDiets.size(); i++) {
//            System.out.println("eleenty listy  " + bodyDiets.get(i).getId() + " ");
//        }

        return "views/diet";

    }

    @GetMapping("/anotherdays")
    public String showDietpagepost(Long id, Model model, RedirectAttributes ra, Principal principal, HttpSession session, SessionStatus status) {

        DetailsDiet detailsDiet = detailsDietRepository.findOne(id);
        List<BodyDiet> bodyDiets = detailsDiet.getBodyDiets();

        session.setAttribute("findday", detailsDiet.getId());
        model.addAttribute("bodyDiets", bodyDiets);
        System.out.println("przchwycone id " + id);


        return "redirect:/diet";
    }


    @PostMapping("/finddiet")
    public String addTask(Model model, Long iloscposilkow, Long liczbakalori, Principal principal) {

        String emial = principal.getName();
        User user = userService.findOne(emial);
        Diet diet = dietService.findOneDiet(iloscposilkow, liczbakalori);
        user.setDietid(diet.getId());
        user.setHavadiet(true);
        userService.save(user);

        return "redirect:/diet";
    }

    @GetMapping("/cheangediet")
    public String cheangediet(Principal principal) {

        String emial = principal.getName();
        User user = userService.findOne(emial);
        user.setDietid(null);
        user.setHavadiet(false);
        userService.save(user);
        return "redirect:/diet";
    }

}

