package sport.application.services;

import sport.application.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {


    @Autowired
    private ExerciseRepository exerciseRepository;



//    public List<Weight> findUserWeight(User user){
//        return weightRepository.findByUser(user);
//    }

}
