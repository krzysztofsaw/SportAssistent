package sport.application.services;


import sport.application.entities.Goals;
import sport.application.entities.User;
import sport.application.repositories.GoalsRepository;
import sport.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class GoalsService {

    @Autowired
    private GoalsRepository goalsRepository;
    @Autowired
    private UserRepository userRepository;


    public void addGoals(Goals goals, User user) {
        goals.setUser(user);
        goalsRepository.save(goals);
    }

    public List<Goals> findUserGoals(User user){
        return goalsRepository.findByUser(user);
    }

    public Goals findOne(Long id) {
        return goalsRepository.findOne(id);

    }

    public void save(Goals goals) {

        goalsRepository.save(goals);
    }

//    @Override
    public void deltegoals(Long id) {
        goalsRepository.delete(id);
    }

//    public List<Goals> findByUser(User user) {
//        return goalsRepository.findByUser(user);
//    }


//    public Goals getOne(Long id) {
//        return goalsRepository.getById(id);
//    }



//    @PostConstruct
//    public void loaddate(){
//
//    }
}
