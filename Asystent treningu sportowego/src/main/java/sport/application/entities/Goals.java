package sport.application.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Goals {


    @Id
    @GeneratedValue
    private Long id;
    private String nazwa;
    private String typeactivity;
    private String typegoals;
    private Integer goal;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private  Date stopgoals;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startgoals;
    private Integer curentresult;

    @ManyToOne
    @JoinColumn(name = "AdresEmailUzytkownika")
    private User user;

    public Goals() {

    }

    public Goals(Integer curentresult) {
        this.curentresult = curentresult;
    }

    public String getTypegoals() {
        return typegoals;
    }

    public Date getStopgoals() {
        return stopgoals;
    }

    public void setStopgoals(Date stopgoals) {
        this.stopgoals = stopgoals;
    }

    public Integer getCurentresult() {
        return curentresult;
    }

    public void setCurentresult(Integer curentresult) {
        this.curentresult = curentresult;
    }

    public void setTypegoals(String typegoals) {
        this.typegoals = typegoals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getTypeactivity() {
        return typeactivity;
    }

    public void setTypeactivity(String typeactivity) {
        this.typeactivity = typeactivity;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Date getStartgoals() {
        return startgoals;
    }

    public void setStartgoals(Date startgoals) {
        this.startgoals = startgoals;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Goals(String nazwa, Integer curentresult, String typeactivity, String  typegoals, Integer goal, Date startgoals, Date stopgoals, User user) {
        this.nazwa = nazwa;
        this.typeactivity = typeactivity;
        this.typegoals = typegoals;
        this.goal = goal;
        this.startgoals = startgoals;
        this.stopgoals =stopgoals;
        this.curentresult=curentresult;
       this.user = user;
    }

    public Goals(String typeactivity,String typegoals ) {
        this.typeactivity = typeactivity;
        this.typegoals =typegoals;
    }

    public Goals(String nazwa,Integer curentresult, String typeactivity,String typegoals, Integer goal, Date startgoals) {
        this.nazwa = nazwa;
        this.typeactivity = typeactivity;
        this.typegoals = typegoals;
        this.goal = goal;
        this.startgoals = startgoals;
        this.curentresult=curentresult;
        this.stopgoals =startgoals;
    }


}
