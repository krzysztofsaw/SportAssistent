package sport.application.controllers;

import sport.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@ControllerAdvice
public class IndexController {

	@Autowired
	private UserService userService;
	  
	@GetMapping("/")
	public String showIndexPage(Model model) {
		return "index";

	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "views/loginForm";
	}







}
