package sport.application.services;

import sport.application.entities.Diet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sport.application.repositories.BodyDietRepository;
import sport.application.repositories.DetailsDietRepository;
import sport.application.repositories.DietRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DietService {

    @Autowired
    private DietRepository dietRepository;

    private long returnCalories(List<Long> list, long calories) {

        long element = Math.abs(list.get(0) - calories);
        long idx = 0;
        for (long c = 1; c < list.size(); c++) {
            long find = Math.abs(list.get((int) c) - calories);
            if (find < element) {
                idx = c;
                element = find;
            }
        }
        return list.get((int) idx);
    }


    public Diet findOneDiet(Long iloscPosilkow, Long liczbaKalori) {


        List<Long> listazkaloriami = new ArrayList<>();

        List<Diet> diety = dietRepository.findAll();

        for (Diet diet : diety) {
            if (iloscPosilkow.equals(diet.getIloscposilkow())) {
                listazkaloriami.add(diet.getLiczbakalori());
            }
        }

        long calories = returnCalories(listazkaloriami,liczbaKalori);
        System.out.println("wybrano wartość"+ calories);

        Diet findDiet = findDietbyKalorie(iloscPosilkow, calories);

        System.out.println("Dobrana dieta ma id "+ findDiet.getId());

        return findDiet;


    }

    public Diet findDietbyKalorie(Long iloscPosilkow, Long calories) {
        List<Diet> diets = dietRepository.findAll();
        for (Diet diet : diets) {
            if (iloscPosilkow.equals(diet.getIloscposilkow())) {
                if (calories.equals(diet.getLiczbakalori())) {
                    return dietRepository.findOne(diet.getId());
                }
            }
        }
        return null;
    }

}
