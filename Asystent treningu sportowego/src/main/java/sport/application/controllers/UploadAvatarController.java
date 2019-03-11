package sport.application.controllers;

//import com.oan.management.exception.StorageFileNotFoundException;
//import com.oan.management.model.User;
//import com.oan.management.service.StorageImageService.StorageService;
//import com.oan.management.service.user.UserService;
import sport.application.entities.User;
import sport.application.exception.StorageFileNotFoundException;
import sport.application.services.UserService;
import sport.application.services.StorageImageService.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class UploadAvatarController {

    private final StorageService storageService;

    @Autowired
    private UserService userService;

    @Autowired
    public UploadAvatarController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/upload-avatar")
    public String uploadAvatarPage(Model model, Authentication authentication, HttpServletRequest req,Principal principal) {

        String email = principal.getName();
        model.addAttribute("user", userService.findOne(email));

//        userService.updateUserAttributes(userLogged, req);
        return "/views/editprofile";
    }

    @PostMapping("/upload-avatar")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Authentication authentication, Principal principal) {
        String email = principal.getName();
        User user = userService.findOne(email);
        if (storageService.isCorrectImageType(file)) {
            storageService.store(file, user.getEmail());
            return "redirect:/upload-avatar?success";
        } else {
            return "redirect:/upload-avatar?wrongtype";
        }
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }



    @GetMapping("/api/avatar/{email:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String email,Principal principal) {

        Resource file;
        try {
            file = storageService.loadAsResource(email+".jpg");


        } catch (StorageFileNotFoundException e) {
            file = storageService.loadAsResource("0.jpg");
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}