package sport.application.services;

import java.util.*;

import sport.application.entities.Role;
import sport.application.entities.User;
import sport.application.repositories.RoleRepository;
import sport.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    boolean isencoder = true;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    public boolean createUser(User user) {
        if (isencoder) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRepassword(encoder.encode(user.getRepassword()));
            isencoder = false;
        }
        Role userRole = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return true;
    }


    public User findOne(String email) {
        return userRepository.findOne(email);

    }

    public void save(User user) {

        Role userRole = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public boolean isUserPresent(String email) {
        User u = userRepository.findOne(email);
        return u != null;

    }


    public boolean usercreate(User user) {

        return createUser(user) == true;
    }
    //validate
    public boolean isPasswordAndRepasswordTheSame(String password, String repassword) {

        return password.equals(repassword) == true;
    }

    public boolean fbnotempty(String password, String repassword) {

        return !password.isEmpty() || !repassword.isEmpty();
    }

    public boolean fblenght(String password, String repassword) {

        return password.length() >= 4 || repassword.length() >= 4;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findByName(String name) {
        return userRepository.findByNameLike("%" + name + "%");
    }

    public String getUserByUsername(String currentUser) {
        return currentUser;

    }


}
