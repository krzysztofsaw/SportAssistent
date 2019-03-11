package sport.application.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.logging.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
public class BodyDiet{

    @Id
    private Long id;
    private String rodzajposilku;
    private String nazwaposilku;
    private Integer liczbakalori;
    private Integer wagaporcji;
    @Column(name="skladniki", length=2000)
    private String skladniki;
    @Column(name="przepis", length=2000)
    private String przepis;


    public String getRodzajposilku() {
        return rodzajposilku;
    }

    public void setRodzajposilku(String rodzajposilku) {
        this.rodzajposilku = rodzajposilku;
    }

    public String getNazwaposilku() {
        return nazwaposilku;
    }

    public void setNazwaposilku(String nazwaposilku) {
        this.nazwaposilku = nazwaposilku;
    }

    public Integer getLiczbakalori() {
        return liczbakalori;
    }

    public void setLiczbakalori(Integer liczbakalori) {
        this.liczbakalori = liczbakalori;
    }

    public Integer getWagaporcji() {
        return wagaporcji;
    }

    public void setWagaporcji(Integer wagaporcji) {
        this.wagaporcji = wagaporcji;
    }

    public String getSkladniki() {
        return skladniki;
    }

    public void setSkladniki(String skladniki) {
        this.skladniki = skladniki;
    }

    public String getPrzepis() {
        return przepis;
    }

    public void setPrzepis(String przepis) {
        this.przepis = przepis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BodyDiet() {

    }


}
