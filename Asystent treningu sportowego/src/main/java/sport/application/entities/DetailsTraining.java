package sport.application.entities;

import javax.persistence.*;

@Entity
public class DetailsTraining {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="notatka", length=2000)
    private String notatka;

    @ManyToOne
    @JoinColumn(name = "ID_treninguday")
    private TrainingDay trainingDay;

    @ManyToOne
    @JoinColumn(name = "AdresEmailUzytkownika")
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNotatka() {
        return notatka;
    }

    public void setNotatka(String notatka) {
        this.notatka = notatka;
    }

    public TrainingDay getTrainingDay() {
        return trainingDay;
    }

    public void setTrainingDay(TrainingDay trainingDay) {
        this.trainingDay = trainingDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetailsTraining() {

    }


}
