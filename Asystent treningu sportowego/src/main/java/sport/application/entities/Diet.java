package sport.application.entities;

import javax.persistence.*;

import java.util.List;

@Entity
public class Diet {

    @Id
    private Long id;
    private Long iloscposilkow;
    private Long liczbakalori;

    @OneToMany
    @JoinColumn(name = "Day_diet_id")
    private List<DetailsDiet> detailsDiets;

    public List<DetailsDiet> getDetailsDiets() {
        return detailsDiets;
    }

    public void setDetailsDiets(List<DetailsDiet> detailsDiets) {
        this.detailsDiets = detailsDiets;
    }

    //    @ManyToOne
//    @JoinColumn(name = "AdresEmailUzytkownika")
//    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIloscposilkow() {
        return iloscposilkow;
    }

    public void setIloscposilkow(Long iloscposilkow) {
        this.iloscposilkow = iloscposilkow;
    }

    public Long getLiczbakalori() {
        return liczbakalori;
    }

    public void setLiczbakalori(Long liczbakalori) {
        this.liczbakalori = liczbakalori;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Diet() {

    }

}
