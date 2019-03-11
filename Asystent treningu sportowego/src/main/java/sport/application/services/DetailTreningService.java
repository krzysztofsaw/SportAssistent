package sport.application.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sport.application.entities.DetailsTraining;
import sport.application.entities.User;
import sport.application.repositories.DetailsTrainingRepository;
import sport.application.repositories.UserRepository;

import java.util.List;

@Service
public class DetailTreningService {

    @Autowired
    private DetailsTrainingRepository detailsTrainingRepository;

    @Autowired
    private UserRepository userRepository;

    public DetailsTraining addanotegert(User user, Long id) {

        List<DetailsTraining> detailsTrainings = detailsTrainingRepository.findAll();

        for (DetailsTraining trainingday : detailsTrainings) {
            System.out.println("wartosc1 " + trainingday.getUser().getEmail().equals(user.getEmail()));
            System.out.println("wartosc2 " + id.equals(trainingday.getTrainingDay().getId()));
            System.out.println("wyslane id " + id);
            System.out.println("wyslane  traning day id " + trainingday.getTrainingDay().getId());

          if(trainingday.getUser().getEmail().equals(user.getEmail())&& id.equals(trainingday.getTrainingDay().getId())){
              DetailsTraining detailsTraining = detailsTrainingRepository.findOne(trainingday.getId());

             System.out.println("Znalezione id z ifa to " + detailsTraining.getId());
              return detailsTraining;
          }
        }
//        System.out.println("custom details" + detailsTraining.getId());
        return new DetailsTraining();
    }

}
