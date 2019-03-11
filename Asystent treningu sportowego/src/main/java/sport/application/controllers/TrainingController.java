package sport.application.controllers;

import javax.servlet.http.HttpSession;

import sport.application.entities.DetailsTraining;
import sport.application.entities.Training;
import sport.application.entities.TrainingDay;
import sport.application.entities.User;
import sport.application.repositories.PlanRepository;
import sport.application.repositories.DetailsTrainingRepository;
import sport.application.repositories.TrainingDayRepository;
import sport.application.repositories.TrainingRepository;
import sport.application.services.DetailTreningService;
import sport.application.services.TrainingDayService;
import sport.application.services.TrainingService;
import sport.application.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.*;

@Controller
public class TrainingController {

    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private DetailsTrainingRepository detailsTrainingRepository;
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private DetailTreningService detailTreningService;
    @Autowired
    private TrainingDayRepository trainingDayRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/training")
    public String taskForm(String email, Model model, HttpSession session, Principal principal) {
        String emial = principal.getName();
        User user = userService.findOne(emial);

        List<String> celtreningu = new ArrayList<>(Arrays.asList("odchudzanie", "kondycja", "masa", "rzeźba"));
        List<String> stopienzawansowania = new ArrayList<>(Arrays.asList("początkujący", "zaawansowany", "amator"));
        List<String> ilosctreningow = new ArrayList<>(Arrays.asList("2", "3", "4", "5"));
        List<String> iloscczasu = new ArrayList<>(Arrays.asList("30min", "60min", "90min"));
//        List<String> miejscecwiczen = new ArrayList<>(Arrays.asList( "dom", "siłownia"));

        model.addAttribute("celtreningu", celtreningu);
        model.addAttribute("stopienzawansowania", stopienzawansowania);
        model.addAttribute("ilosctreningow", ilosctreningow);
        model.addAttribute("iloscczasu", iloscczasu);
        model.addAttribute("training", new Training());

        System.out.println(user.isHavaplain());
        //jesli nie ma planow
        if (!user.isHavaplain()) {
            //pokaz ankiete
            model.addAttribute("userhaveplain", true);
            model.addAttribute("checktreining", false);
        }

//jesli ma trenign
        if (user.isHavaplain()) {
            Long aidik = user.getTrainingid();
            model.addAttribute("checktreining", true);

            Training training = trainingRepository.findOne(aidik); // znaleziony konkretni trening
            List<TrainingDay> trainingDays = training.getTrainingDays();
            model.addAttribute("trninddays", trainingDays);
        }

        return "views/training";
    }



    @PostMapping("/training")
    public String addTask(Principal principal, String cel, String stopienzaawansowania, String iloscczasu, String ilosctreningow) {


        List<Training> listatreningow = trainingRepository.findAll();

        String emial = principal.getName();
        User user = userService.findOne(emial);

          long selectid = trainingService.findOneTraining(listatreningow,cel,stopienzaawansowania,iloscczasu,ilosctreningow);


//        System.out.println(cel);
//        System.out.println(stopienzaawansowania);
//        System.out.println(iloscczasu);
//        System.out.println(ilosctreningow);
//
//        for (int i = 0; i < lista1.size(); i++) {
//            System.out.println("1 tab " + lista1.get(i) + " ");
//        }
//        for (int i = 0; i < lista2.size(); i++) {
//            System.out.println("2 tab " + lista2.get(i) + " ");
//        }
//        for (int i = 0; i < lista3.size(); i++) {
//            System.out.println("3 tab " + lista3.get(i) + " ");
//        }
//        for (int i = 0; i < lista4.size(); i++) {
//            System.out.println("4 tab " + lista4.get(i) + " ");
//        }
//        System.out.println("zgadzajce sie wartosci " + a);
//        System.out.println("zgadzajce sie wartosci " + b);
//        System.out.println("zgadzajce sie wartosci " + c);
//        System.out.println("zgadzajce sie wartosci " + d);
//        System.out.println("Wybrany trening ma id " + wybraneid);

        user.setTrainingid(selectid);
        user.setHavaplain(true);
        userService.save(user);


        return "redirect:/training";
    }


    @GetMapping("/cheange")
    public String cheange(Principal principal) {

        String emial = principal.getName();
        User user = userService.findOne(emial);

        user.setTrainingid(null);
        user.setHavaplain(false);
        userService.save(user);
        return "redirect:/training";
    }

    @PostMapping("/addanote")
    public String addnotepost(String notatka, HttpSession session, Principal principal) {
        String emial = principal.getName();
        User user = userService.findOne(emial);

        Long aidik = (Long) session.getAttribute("currnetexerisetrening");
        TrainingDay trainingDaynew = trainingDayRepository.findOne(aidik);


        Long zmienna = (Long) session.getAttribute("details");
        Long iddd = (Long) session.getAttribute("idddd");

        if (zmienna == null) {
            DetailsTraining nowy = new DetailsTraining();
            nowy.setTrainingDay(trainingDaynew);
            nowy.setUser(user);
            nowy.setNotatka(notatka);
            session.removeAttribute("details");
            detailsTrainingRepository.save(nowy);
            session.removeAttribute("idddd");
        }

        if (zmienna != null) {
            System.out.println("przechwycone iddd " + iddd);
            System.out.println("przechwycone details " + zmienna);
            System.out.println(" czy jest w bazie " + iddd.equals(zmienna));

            if(iddd.equals(zmienna)) {
                DetailsTraining detailsTraining = detailTreningService.addanotegert(user, iddd);
                detailsTraining.setNotatka(notatka);
                session.removeAttribute("details");
                detailsTrainingRepository.save(detailsTraining);
                session.removeAttribute("idddd");


            }if(!iddd.equals(zmienna)) {
                DetailsTraining inny = new DetailsTraining();
                inny.setUser(user);
                inny.setTrainingDay(trainingDaynew);
                inny.setNotatka(notatka);
                session.removeAttribute("details");
                detailsTrainingRepository.save(inny);
                session.removeAttribute("idddd");
            }
        }
        return "redirect:/training";
    }

    @GetMapping("/addanote")
    @ResponseBody
    public DetailsTraining addanotegert(Long id, HttpSession session, Principal principal) {
        String emial = principal.getName();
        User user = userService.findOne(emial);
        TrainingDay trainingDaynew = trainingDayRepository.findOne(id);

        session.setAttribute("currnetexerisetrening", trainingDaynew.getId());

        System.out.println("id konkretngo cwiczenia ma id  " + trainingDaynew.getId());

        DetailsTraining detailsTraining = detailTreningService.addanotegert(user, id);

        if (detailsTraining.getId() == null) {
            session.removeAttribute("details");
            session.removeAttribute("idddd");
        }
        if (detailsTraining.getId() != null) {

            session.setAttribute("idddd", id);
            session.setAttribute("details", detailsTraining.getTrainingDay().getId());

            System.out.println(" details id" + detailsTraining.getTrainingDay().getId());
            System.out.println(" idddd id" + id);
//

            return detailsTraining;

        }

        return new DetailsTraining();
    }

}

