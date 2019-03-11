package sport.application.services;

import sport.application.entities.BodyDimensions;
import sport.application.entities.User;
import sport.application.repositories.BodyDimensionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyDimensionsService {

    @Autowired
    private BodyDimensionsRepository bodyDimensionsRepository;

    public void addDimensions(BodyDimensions bodyDimensions, User user) {
        bodyDimensions.setUser(user);
        bodyDimensionsRepository.save(bodyDimensions);
    }

    public List<BodyDimensions> findUserDimenasions(User user){
        return bodyDimensionsRepository.findByUser(user);
    }


}
