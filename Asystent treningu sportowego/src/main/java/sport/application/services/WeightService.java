package sport.application.services;

import sport.application.entities.User;
import sport.application.entities.Weight;
import sport.application.repositories.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightService {


    @Autowired
    private WeightRepository weightRepository;

    public void addWeight(Weight weight, User user) {
        weight.setUser(user);
        weightRepository.save(weight);
    }

    public List<Weight> findUserWeight(User user){
        return weightRepository.findByUser(user);
    }
}
