package sport.application.services;


import sport.application.entities.Training;
import sport.application.repositories.TrainingRepository;
import sport.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private UserRepository userRepository;

    Long getRandomNumber(List<Long> arr, int size) {
        Random rand = new Random();
        long randomElement = Math.toIntExact(arr.get(rand.nextInt(size)));
        return randomElement;
    }



    public long findOneTraining(List<Training> listatreningow,String cel, String stopienzaawansowania, String iloscczasu, String ilosctreningow ) {
        int a = 0, b = 0, c = 0, d = 0;
        List<Long> lista1 = new ArrayList<>();
        List<Long> lista2 = new ArrayList<>();
        List<Long> lista3 = new ArrayList<>();
        List<Long> lista4 = new ArrayList<>();
        long selectId = 0;

        for (Training train : listatreningow) {

            if (train.getCel().equals(cel) || train.getIloscczasu().equals(iloscczasu) || train.getStopienzaawansowania().equals(stopienzaawansowania) || train.getIlosctreningow().equals(ilosctreningow)) {
                lista1.add(a, train.getId());
                a++;
            }
            if (train.getCel().equals(cel) && train.getIloscczasu().equals(iloscczasu) || (train.getCel().equals(cel) && train.getStopienzaawansowania().equals(stopienzaawansowania)) || (train.getCel().equals(cel) && train.getIlosctreningow().equals(ilosctreningow)) || (train.getStopienzaawansowania().equals(stopienzaawansowania) && train.getIlosctreningow().equals(ilosctreningow)) || (train.getStopienzaawansowania().equals(stopienzaawansowania) && train.getIloscczasu().equals(iloscczasu)) || (train.getIlosctreningow().equals(ilosctreningow) && train.getIloscczasu().equals(iloscczasu))) {
                lista2.add(b, train.getId());
                b++;
            }
            if (train.getCel().equals(cel) && train.getStopienzaawansowania().equals(stopienzaawansowania) && train.getIlosctreningow().equals(ilosctreningow) || (train.getCel().equals(cel) && train.getStopienzaawansowania().equals(stopienzaawansowania) && train.getIloscczasu().equals(iloscczasu)) || (train.getStopienzaawansowania().equals(stopienzaawansowania) && train.getIlosctreningow().equals(ilosctreningow) && train.getIloscczasu().equals(iloscczasu)) || (train.getIlosctreningow().equals(ilosctreningow) && train.getIloscczasu().equals(iloscczasu) && train.getCel().equals(cel))) {
                lista3.add(c, train.getId());
                c++;
            }
            if (train.getCel().equals(cel) && train.getIloscczasu().equals(iloscczasu) && train.getStopienzaawansowania().equals(stopienzaawansowania) && train.getIlosctreningow().equals(ilosctreningow)) {
                lista4.add(d, train.getId());
                d++;
            }
        }

        if (a != 0) {
            selectId = getRandomNumber(lista1, lista1.size());
        }
        if (b != 0) {
            selectId = getRandomNumber(lista2, lista2.size());
        }
        if (c != 0) {
            selectId = getRandomNumber(lista3, lista3.size());
        }
        if (d != 0) {
            selectId = getRandomNumber(lista4, lista4.size());
        } else if (a == 0 && b == 0 && c == 0 && d == 0) {
            System.out.println("Brak planu");
        }




        for (int i = 0; i < lista1.size(); i++) {
            System.out.println("1 tab " + lista1.get(i) + " ");
        }
        for (int i = 0; i < lista2.size(); i++) {
            System.out.println("2 tab " + lista2.get(i) + " ");
        }
        for (int i = 0; i < lista3.size(); i++) {
            System.out.println("3 tab " + lista3.get(i) + " ");
        }
        for (int i = 0; i < lista4.size(); i++) {
            System.out.println("4 tab " + lista4.get(i) + " ");
        }
        System.out.println("zgadzajce sie wartosci " + a);
        System.out.println("zgadzajce sie wartosci " + b);
        System.out.println("zgadzajce sie wartosci " + c);
        System.out.println("zgadzajce sie wartosci " + d);
        System.out.println("Wybrany trening ma id " + selectId);


    return selectId;
    }



    public void test(List<Long> lista1, List<Long> lista2, List<Long> lista3,List<Long> lista4,String cel, String stopienzaawansowania, String iloscczasu, String ilosctreningow, int a, int b,int c,int d){

        System.out.println(cel);
        System.out.println(stopienzaawansowania);
        System.out.println(iloscczasu);
        System.out.println(ilosctreningow);

        for (int i = 0; i < lista1.size(); i++) {
            System.out.println("1 tab " + lista1.get(i) + " ");
        }
        for (int i = 0; i < lista2.size(); i++) {
            System.out.println("2 tab " + lista2.get(i) + " ");
        }
        for (int i = 0; i < lista3.size(); i++) {
            System.out.println("3 tab " + lista3.get(i) + " ");
        }
        for (int i = 0; i < lista4.size(); i++) {
            System.out.println("4 tab " + lista4.get(i) + " ");
        }
        System.out.println("zgadzajce sie wartosci " + a);
        System.out.println("zgadzajce sie wartosci " + b);
        System.out.println("zgadzajce sie wartosci " + c);
        System.out.println("zgadzajce sie wartosci " + d);
//        System.out.println("Wybrany trening ma id " + selectId);
    }

}
