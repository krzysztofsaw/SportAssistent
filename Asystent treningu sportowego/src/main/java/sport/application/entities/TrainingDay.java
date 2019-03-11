package sport.application.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
public class TrainingDay {

    @Id
    private Long id;
    private String dzientygodnia;
    private String nazwacwiczenia;
    private Long iloscpowtorzen;
    private  Long iloscseri;
    @Column(name="opis", length=2000)
    private  String opis;


    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "TrainingDay_ID")
    private List<DetailsTraining> trainingDayExercises;



    public String getNazwacwiczenia() {
        return nazwacwiczenia;
    }

    public void setNazwacwiczenia(String nazwacwiczenia) {
        this.nazwacwiczenia = nazwacwiczenia;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Long getIloscpowtorzen() {
        return iloscpowtorzen;
    }

    public void setIloscpowtorzen(Long iloscpowtorzen) {
        this.iloscpowtorzen = iloscpowtorzen;
    }

    public Long getIloscseri() {
        return iloscseri;
    }

    public void setIloscseri(Long iloscseri) {
        this.iloscseri = iloscseri;
    }

    public String getDzientygodnia() {
        return dzientygodnia;
    }

    public void setDzientygodnia(String dzientygodnia) {
        this.dzientygodnia = dzientygodnia;
    }

    public List<DetailsTraining> getTrainingDayExercises() {
        return trainingDayExercises;
    }

    public void setTrainingDayExercises(List<DetailsTraining> trainingDayExercises) {
        this.trainingDayExercises = trainingDayExercises;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrainingDay() {

    }


}
