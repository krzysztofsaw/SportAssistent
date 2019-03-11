package sport.application.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.logging.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Exercise {

    @Id
    private Long id;
    private String nazwa;
    @Column(name="opis", length=2000)
    private String opis;


    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exercise() {

    }

}
