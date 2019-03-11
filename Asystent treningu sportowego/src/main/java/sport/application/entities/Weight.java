package sport.application.entities;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Weight {

    @Id
    @GeneratedValue
    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date currentdate;
    private Integer waga;

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

    public Integer getWaga() {
        return waga;
    }

    public void setWaga(Integer waga) {
        this.waga = waga;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Weight(Date currentdate, Integer waga, User user) {
        this.currentdate = currentdate;
        this.waga = waga;
        this.user = user;
    }
    public Weight(Date currentdate, Integer waga) {
        this.currentdate = currentdate;
        this.waga = waga;

    }

    public Weight() {

    }
}
