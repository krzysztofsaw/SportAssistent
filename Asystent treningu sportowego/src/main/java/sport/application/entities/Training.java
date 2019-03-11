package sport.application.entities;

import javax.persistence.*;

import java.util.List;

@Entity
public class Training {

    @Id
    private Long id;
    private String cel;
    private String stopienzaawansowania;
    private String iloscczasu;
    private String ilosctreningow;

    @OneToMany
    @JoinColumn(name = "Training_ID")
    private List<TrainingDay> trainingDays;

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getStopienzaawansowania() {
        return stopienzaawansowania;
    }

    public void setStopienzaawansowania(String stopienzaawansowania) {
        this.stopienzaawansowania = stopienzaawansowania;
    }

    public String getIlosctreningow() {
        return ilosctreningow;
    }

    public void setIlosctreningow(String ilosctreningow) {
        this.ilosctreningow = ilosctreningow;
    }

    public String getIloscczasu() {
        return iloscczasu;
    }

    public void setIloscczasu(String iloscczasu) {
        this.iloscczasu = iloscczasu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TrainingDay> getTrainingDays() {
        return trainingDays;
    }

    public void setTrainingDays(List<TrainingDay> trainingDays) {
        this.trainingDays = trainingDays;
    }

    public Training() {

    }
}
