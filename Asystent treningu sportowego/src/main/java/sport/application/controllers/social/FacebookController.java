package sport.application.controllers.social;

import sport.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class FacebookController {

    String fimie, fnazwisko, femail, userpicture;

    @Autowired
    private UserService userService;
    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    @Autowired
    protected AuthenticationManager authenticationManager;

    public FacebookController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }


    @GetMapping("/loginfacebook")
    public String connectToFacebook(Model model) {

        return "loginfacebook";
    }

    @GetMapping("/facebookconnect")
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/loginfacebook";
        }

        User userfb = facebook.userOperations().getUserProfile();
        fimie = userfb.getFirstName();
        fnazwisko = userfb.getLastName();
        femail = userfb.getEmail();
        userpicture = userfb.getId();

        model.addAttribute("userfb", new sport.application.entities.User());

        if(userService.isUserPresent(femail)==true) {
            model.addAttribute("exist",true);
            return "views/loginForm";
        }

        if (userService.isUserPresent(femail) == true) {

            Authentication authentication = new UsernamePasswordAuthenticationToken(femail, "xxxx",
                    AuthorityUtils.createAuthorityList("ROLE_USER"));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "views/profile";
        }


        String fbImg = "http://graph.facebook.com/" + userpicture + "/picture?height=100&width=100";
        model.addAttribute("userimg", fbImg);
        model.addAttribute("fuser_name", fimie);
        model.addAttribute("fsurname", fnazwisko);


        return "views/logedfacebook";


    }

    @PostMapping("/facebookconnect")
    public String registerUser(@Valid sport.application.entities.User user, BindingResult bindingResult, Model model) {

        model.addAttribute("userfb", user);

        String fbImg = "http://graph.facebook.com/" + userpicture + "/picture?height=100&width=100";
        model.addAttribute("userimg", fbImg);
        model.addAttribute("fuser_name", fimie);
        model.addAttribute("fsurname", fnazwisko);


        if ((userService.isPasswordAndRepasswordTheSame(user.getPassword(), user.getRepassword())) == true && (userService.fbnotempty(user.getPassword(), user.getRepassword())) == true && (userService.fblenght(user.getPassword(), user.getRepassword())) == true) {

            sport.application.entities.User newUser = new sport.application.entities.User(femail, fimie,null, user.getPassword(), fnazwisko, user.getRepassword(),false ,false ,null);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRepassword(encoder.encode(user.getRepassword()));
            userService.createUser(newUser);
            Authentication authentication = new UsernamePasswordAuthenticationToken(femail, user.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_USER"));
            SecurityContextHolder.getContext().setAuthentication(authentication);



        } else {

            if ((userService.fbnotempty(user.getPassword(), user.getRepassword())) == false) {
                model.addAttribute("fpuste", true);
            }
            else if ((userService.fblenght(user.getPassword(), user.getRepassword())) == false) {
                model.addAttribute("fdlugosc", true);
            }
            else if ((userService.isPasswordAndRepasswordTheSame(user.getPassword(), user.getRepassword())) == false) {
                model.addAttribute("frowne", true);
            }


            return "views/logedfacebook";
        }


        return "views/profile";


    }


}


