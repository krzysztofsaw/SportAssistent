package sport.application.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User {

    @Id
    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;
    @NotEmpty
    @Pattern(regexp = "[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]*", message = "Niedozwole znaki")
    private String name;
    @NotEmpty
    @Pattern(regexp = "[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]*", message = "Niedozwole znaki")
    private String surname;
    @NotEmpty
    private String password;
    @NotEmpty
    private String repassword;
    private String gender;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
    private Integer growth;
    private boolean havaplain = false;
    private boolean havadiet = false;
    private Long trainingid;
    private Long dietid;



    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_EMAIL", referencedColumnName = "email")}, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_NAME", referencedColumnName = "name")})
    private List<Role> roles;

    public Long getTrainingid() {
        return trainingid;
    }

    public void setTrainingid(Long trainingid) {
        this.trainingid = trainingid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }


    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public Long getDietid() {
        return dietid;
    }

    public void setDietid(Long dietid) {
        this.dietid = dietid;
    }


    public boolean isHavadiet() {
        return havadiet;
    }

    public void setHavadiet(boolean havadiet) {
        this.havadiet = havadiet;
    }

    public boolean isHavaplain() {
        return havaplain;
    }

    public void setHavaplain(boolean havaplain) {
        this.havaplain = havaplain;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

//    public List<Task> gettasks() {
//        return tasks;
//    }
//
//    public void settasks(List<Task> tasks) {
//        this.tasks = tasks;
//    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User(String email, String name, Long trainingid, String password, String surname, String repassword, boolean havaplain, boolean havadiet, Long dietid) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.surname = surname;
        this.repassword = repassword;
        this.havaplain = havaplain;
        this.trainingid = trainingid;
        this.havadiet = havadiet;
        this.dietid = dietid;
    }

    public User() {

    }

}
