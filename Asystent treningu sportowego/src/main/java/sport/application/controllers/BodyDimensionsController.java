package sport.application.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import sport.application.entities.BodyDimensions;
import sport.application.entities.User;
import sport.application.services.BodyDimensionsService;
import sport.application.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BodyDimensionsController {

    @Autowired
    private BodyDimensionsService bodyDimensionsService;
    @Autowired
    private UserService userService;

    @GetMapping("/addDimensions")
    public String addDimensions(Model model, Principal principal) {


        model.addAttribute("havadimension", false);
        String useremail = principal.getName();
        User user = userService.findOne(useremail);
        model.addAttribute("dimansions", bodyDimensionsService.findUserDimenasions(user));
        model.addAttribute("newDiemnsions", new BodyDimensions());

        List<BodyDimensions> listaa = bodyDimensionsService.findUserDimenasions(user);
        if(listaa.size()!=0){
            model.addAttribute("havadimension", true);
        }


        List<String> datapomiaru = new ArrayList<>();
        List<Integer> bicki = new ArrayList<>();
        List<Integer> biodra = new ArrayList<>();
        List<Integer> kark = new ArrayList<>();
        List<Integer> klatka = new ArrayList<>();
        List<Integer> lydka = new ArrayList<>();
        List<Integer> przedramie = new ArrayList<>();
        List<Integer> talia = new ArrayList<>();
        List<Integer> udo = new ArrayList<>();

        for (BodyDimensions aListaa : listaa) {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.UK);
            String date = dt.format(aListaa.getCurrentdate());
            System.out.println(date);
            datapomiaru.add(date);
        }
        for (BodyDimensions aListaa : listaa) {
            bicki.add(aListaa.getBiceps());
        }
        for (BodyDimensions aListaa : listaa) {
            biodra.add(aListaa.getBiodra());
        }
        for (BodyDimensions aListaa : listaa) {
            kark.add(aListaa.getKark());
        }
        for (BodyDimensions aListaa : listaa) {
            klatka.add(aListaa.getKlatka());
        }
        for (BodyDimensions aListaa : listaa) {
            lydka.add(aListaa.getLydka());
        }
        for (BodyDimensions aListaa : listaa) {
            przedramie.add(aListaa.getPrzedramie());
        }
        for (BodyDimensions aListaa : listaa) {
            talia.add(aListaa.getTalia());
        }
        for (BodyDimensions aListaa : listaa) {
            udo.add(aListaa.getUdo());
        }


        model.addAttribute("datapomiaru", datapomiaru);
        model.addAttribute("bicki", bicki);
        model.addAttribute("biodra", biodra);
        model.addAttribute("kark", kark);
        model.addAttribute("klatka", klatka);
        model.addAttribute("lydka", lydka);
        model.addAttribute("przedramie", przedramie);
        model.addAttribute("talia", talia);
        model.addAttribute("udo", udo);


        return "views/BodyDimensionsForm";

    }

    @PostMapping("/addDimensions")
    public String addDimensions(@Valid BodyDimensions bodyDimensions, Principal principal) {

        String emial = principal.getName();
        User user = userService.findOne(emial);

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        Date date = new Date();

        bodyDimensions.setCurrentdate(date);
        bodyDimensionsService.addDimensions(bodyDimensions, user);

        return  "redirect:/addDimensions";
    }




}
