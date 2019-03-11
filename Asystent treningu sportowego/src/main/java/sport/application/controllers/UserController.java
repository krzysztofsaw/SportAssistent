package sport.application.controllers;

import sport.application.entities.User;
import sport.application.services.UserService;
import sport.application.services.StorageImageService.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

    @Autowired
    private StorageService storageService;
	
	@GetMapping("/users")
	public String listUsers(Model model, @RequestParam(defaultValue="")  String name) {
		model.addAttribute("users", userService.findByName(name));
		return "views/list";
	}



    @GetMapping("/home")
    public String home(Model model, Principal principal) {

        String email = principal.getName();
        User user = userService.findOne(email);
        String nazwisko= user.getSurname();
        String imie = user.getName();
        model.addAttribute("user_surname", nazwisko);
        model.addAttribute("user_name", imie);
        return "index";
    }

    @GetMapping("/editprofile")
    public String editeProfile(Model model, Principal principal) {

        String email = principal.getName();
        User user = userService.findOne(email);
        model.addAttribute("user", user);
        return "views/editprofile";
    }


    @GetMapping("/editprofile/{email}")
    public String editProfile( @PathVariable String email, Model model) {

        User user = userService.findOne(email);
        model.addAttribute("user", userService.findOne(email));
        userService.save(user);
        return "views/editprofile";
    }



    @PostMapping("/editprofile")
    public String save(@Valid User user, BindingResult bindingResult, RedirectAttributes redirect) {

        if(bindingResult.hasErrors()) {
            return "views/editprofile";
        }

        userService.save(user);

       redirect.addFlashAttribute("success", true);
        return "redirect:/editprofile";
    }



}
