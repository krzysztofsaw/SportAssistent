package sport.application.services;


import sport.application.entities.DetailsDiet;
import sport.application.repositories.BodyDietRepository;
import sport.application.repositories.DetailsDietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyDietService {

    @Autowired
    private BodyDietRepository bodyDietRepository;
    @Autowired
    private DetailsDietRepository detailsDietRepository;

    public DetailsDiet findDetailsFromMonday(List<DetailsDiet> detailsDiets) {


        for (DetailsDiet detailsDiet : detailsDiets) {
            if(detailsDiet.getDzientygodnia().equals("poniedzialek")){
                DetailsDiet detailsDiet1 = detailsDietRepository.findOne(detailsDiet.getId());
                return detailsDiet1;
            }
        }
        return null;
    }

}

