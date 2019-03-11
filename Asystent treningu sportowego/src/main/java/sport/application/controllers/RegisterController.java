package sport.application.controllers;

import javax.validation.Valid;

import sport.application.entities.User;
import sport.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
	@Autowired
	private UserService userService;


	@GetMapping("/register")
	public String registerForm(Model model) {

		model.addAttribute("user", new User());
		return "views/registerForm";
}


	@PostMapping("/register")

    public String registerUser(@Valid User user, BindingResult bindingResult, Model model ) {


		if(bindingResult.hasErrors()) {
			return "views/registerForm";
		}

		if(userService.isUserPresent(user.getEmail())==true) {
			model.addAttribute("exist",true);
			return "views/registerForm";
		}
        if(userService.isPasswordAndRepasswordTheSame(user.getPassword(),user.getRepassword())==false) {
            model.addAttribute("sarowne",true);
            return "views/registerForm";
        }

        if ((userService.fblenght(user.getPassword(), user.getRepassword())) == false) {
			model.addAttribute("fdlugosc", true);
			return "views/registerForm";
		}

		userService.createUser(user);

		if(userService.usercreate(user)==true) {
		model.addAttribute("wartos",true);
		}


		return "index";

	}


}


