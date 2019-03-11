package sport.application.entities;

import javax.persistence.*;

import java.util.List;

@Entity
public class DetailsDiet{

    @Id
    @GeneratedValue
    private Long id;
    private String dzientygodnia;


    @OneToMany
    @JoinColumn(name = "listbodydnia")
    private List<BodyDiet> bodyDiets;

    public List<BodyDiet> getBodyDiets() {
        return bodyDiets;
    }

    public void setBodyDiets(List<BodyDiet> bodyDiets) {
        this.bodyDiets = bodyDiets;
    }

    //    private String rodzajposiłku;
//    private String nazwaposiłku;
//    private Integer liczbakalori;
//    private Integer wagaporcji;
//    private String składniki;
//    private String przepis;

//    @ManyToOne
//    @JoinColumn(name = "ID_diet")
//    private Diet diet;

    public String getDzientygodnia() {
        return dzientygodnia;
    }

    public void setDzientygodnia(String dzientygodnia) {
        this.dzientygodnia = dzientygodnia;
    }
//
//    public Diet getDiet() {
//        return diet;
//    }
//
//    public void setDiet(Diet diet) {
//        this.diet = diet;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetailsDiet() {

    }


}
