package sport.application.services;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public double returnBmi(double waga, double wzrost) {

        double sumabmi = (waga / ((wzrost * 0.01) * (wzrost * 0.01)));

        return sumabmi;
    }

    public double returnBmr(int waga, int wzrost, int wiek, String gender) {
        double sumam;
        double sumak;
        sumam = (9.99 * waga + 6.25 * wzrost - 4.92 * wiek)+5;
        sumak = (9.99 * waga + 6.25 * wzrost - 4.92 * wiek)-161;

        if (gender.equals("male")) {
            return sumam;
        } else
        return sumak;
    }

    public double returnBf(int waga, int obwodpasa, String gender) {
        double a, b, c, d, e, suma;
        a=4.15*obwodpasa;
        b=a/2.54;
        c = 0.082 * waga * 2.2;
        d=0;
        if (gender.equals("male")) {
            d=b-c-98.42;
        }if(gender.equals("famele")){
            d=b-c-76.76;
        }
        e=waga*2.2;
        suma = (d/e)*100;
        return suma;
    }
    public double returnWhr(int obwodpasa, int obbiodra, String gender) {
        double sumam;
        double sumak;
        if (gender.equals("famele")) {
            sumak=obwodpasa/obbiodra;
            return sumak;
        } else
            sumam=obwodpasa/obbiodra;
            return sumam;
    }




}
