package sport.application.services;


import sport.application.repositories.TrainingDayRepository;
import sport.application.repositories.TrainingRepository;
import sport.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TrainingDayService {

    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private TrainingDayRepository trainingDayRepository;
    @Autowired
    private UserRepository userRepository;


}
