package sport.application.entities;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class BodyDimensions {

    @Id
    @GeneratedValue
    private Long id;
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @DateTimeFormat(pattern = "dd-MMM-yyyy HH:mm")
    private Date currentdate;
    private Integer lydka;
    private Integer udo;
    private Integer biodra;
    private Integer talia;
    private Integer klatka;
    private Integer biceps;
    private Integer przedramie;
    private Integer kark;

    @ManyToOne
    @JoinColumn(name = "AdresEmailUzytkownika")
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(Date currentdate) {
        this.currentdate = currentdate;
    }

    public Integer getKark() {
        return kark;
    }

    public void setKark(Integer kark) {
        this.kark = kark;
    }

    public Integer getLydka() {
        return lydka;
    }

    public void setLydka(Integer lydka) {
        this.lydka = lydka;
    }

    public Integer getUdo() {
        return udo;
    }

    public void setUdo(Integer udo) {
        this.udo = udo;
    }

    public Integer getBiodra() {
        return biodra;
    }

    public void setBiodra(Integer biodra) {
        this.biodra = biodra;
    }

    public Integer getTalia() {
        return talia;
    }

    public void setTalia(Integer talia) {
        this.talia = talia;
    }

    public Integer getKlatka() {
        return klatka;
    }

    public void setKlatka(Integer klatka) {
        this.klatka = klatka;
    }

    public Integer getBiceps() {
        return biceps;
    }

    public void setBiceps(Integer biceps) {
        this.biceps = biceps;
    }

    public Integer getPrzedramie() {
        return przedramie;
    }

    public void setPrzedramie(Integer przedramie) {
        this.przedramie = przedramie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public BodyDimensions(Integer lydka,Date currentdate, Integer udo, Integer biodra, Integer talia, Integer klatka, Integer biceps, Integer przedramie, Integer kark) {
        this.lydka = lydka;
        this.udo = udo;
        this.biodra = biodra;
        this.talia = talia;
        this.klatka = klatka;
        this.biceps = biceps;
        this.przedramie = przedramie;
        this.kark=kark;
        this.currentdate=currentdate;
    }

    public BodyDimensions(Integer lydka,Date currentdate, Integer udo, Integer biodra, Integer talia, Integer klatka, Integer biceps, Integer przedramie,Integer kark, User user) {
        this.lydka = lydka;
        this.udo = udo;
        this.biodra = biodra;
        this.talia = talia;
        this.klatka = klatka;
        this.biceps = biceps;
        this.przedramie = przedramie;
        this.kark=kark;
        this.user = user;
        this.currentdate=currentdate;
    }

    public BodyDimensions() {

    }
}
