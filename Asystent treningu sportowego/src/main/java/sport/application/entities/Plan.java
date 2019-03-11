package sport.application.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.logging.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Map;

@Entity
public class Plan {

    @Id
    @GeneratedValue
    private Long id;
//    @ElementCollection
//    private Map<String, String> mapadziencwiczenie;
    private String dzientygodnia;
    private String nazwacwiczneia;
    private String iloscseri;
    private String iloscpowtorzen;

    public String getDzientygodnia() {
        return dzientygodnia;
    }

    public void setDzientygodnia(String dzientygodnia) {
        this.dzientygodnia = dzientygodnia;
    }

    public String getNazwacwiczneia() {
        return nazwacwiczneia;
    }

    public void setNazwacwiczneia(String nazwacwiczneia) {
        this.nazwacwiczneia = nazwacwiczneia;
    }

    public String getIloscseri() {
        return iloscseri;
    }

    public void setIloscseri(String iloscseri) {
        this.iloscseri = iloscseri;
    }

    public String getIloscpowtorzen() {
        return iloscpowtorzen;
    }

    public void setIloscpowtorzen(String iloscpowtorzen) {
        this.iloscpowtorzen = iloscpowtorzen;
    }

//    public Map<String, String> getMapadziencwiczenie() {
//        return mapadziencwiczenie;
//    }
//
//    public void setMapadziencwiczenie(Map<String, String> mapadziencwiczenie) {
//        this.mapadziencwiczenie = mapadziencwiczenie;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plan() {

    }


}
