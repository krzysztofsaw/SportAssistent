
package sport.application.controllers;

import sport.application.services.CalculatorService;
import sport.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DecimalFormat;


@Controller
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @Autowired
    UserService userService;

    public double BMIsuma;
    public double BMRsuma;
    public double BFsuma;
    public double WHRsuma;


    @GetMapping("/kalkulatory")
    public String showCalculatorForm() {
        return "views/calculatorForm";
    }

    @GetMapping("/cal-bmi")
    public String showBmiForm(Model model, Double masa, Double wzrost) {
        model.addAttribute("showscore",true);
        return "views/calculatorViews/bmi";
    }

    @PostMapping("/cal-bmi")
    public String calculateBmiForm(Model model, Integer masa, Integer wzrost) {

        BMIsuma = calculatorService.returnBmi(masa, wzrost);


        model.addAttribute("suma", new DecimalFormat("#0.00").format(BMIsuma));

        if (BMIsuma < 16) {
            model.addAttribute("suma2", new DecimalFormat("#0").format(1));
        } else if (BMIsuma >= 16 && BMIsuma <= 17) {
            model.addAttribute("suma2", new DecimalFormat("#0").format(2));
        } else if (BMIsuma > 17 && BMIsuma <= 18.5) {
            model.addAttribute("suma2", new DecimalFormat("#0").format(3));
        } else if (BMIsuma > 18.5 && BMIsuma <= 25) {
            model.addAttribute("suma2", new DecimalFormat("#0").format(4));
        } else if (BMIsuma > 25 && BMIsuma <= 30) {
            model.addAttribute("suma2", new DecimalFormat("#0").format(5));
        } else if (BMIsuma > 30 && BMIsuma <= 35) {
            model.addAttribute("suma2", new DecimalFormat("#0").format(6));
        } else if (BMIsuma > 35 && BMIsuma <= 40) {
            model.addAttribute("suma2", new DecimalFormat("#0").format(7));
        } else if (BMIsuma > 40) {
            model.addAttribute("suma2", new DecimalFormat("#0").format(8));
        }

        return "views/calculatorViews/bmi";
    }




    @GetMapping("/cal-bmr")
    public String showBmrForm() {
        return "views/calculatorViews/bmr";
    }

    @PostMapping("/cal-bmr")
    public String calculateBmrForm(Model model, Integer masa, Integer wzrost, Integer wiek, String gender) {

        BMRsuma = calculatorService.returnBmr(masa, wzrost, wiek, gender);
        model.addAttribute("suma", new DecimalFormat("#0.0").format(BMRsuma));

        return "views/calculatorViews/bmr";
    }

    @GetMapping("/cal-bf")
    public String showBfForm() {
        return "views/calculatorViews/bf";
    }

    @PostMapping("/cal-bf")
    public String calculateBfForm(Model model, Integer masa, Integer obwodpasa, String gender) {

        BFsuma = calculatorService.returnBf(masa, obwodpasa, gender);
        model.addAttribute("suma", new DecimalFormat("#0.00").format(BFsuma));

        return "views/calculatorViews/bf";
    }

    @GetMapping("/cal-whr")
    public String showWhrForm() {
        return "views/calculatorViews/whr";
    }

    @PostMapping("/cal-whr")
    public String calculateWhrForm(Model model, Integer obwodpasa, Integer obbiodra, String gender) {

        WHRsuma = calculatorService.returnWhr(obwodpasa, obbiodra, gender);
        model.addAttribute("suma", new DecimalFormat("#0.0").format(WHRsuma));

        return "views/calculatorViews/whr";
    }


}
