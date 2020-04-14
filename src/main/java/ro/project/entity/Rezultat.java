package ro.project.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@ToString
public class Rezultat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private Integer rezultatId;

    private Integer scor;

    private String sesiuneId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRezultatId() {
        return rezultatId;
    }

    public void setRezultatId(Integer rezultatId) {
        this.rezultatId = rezultatId;
    }

    public Integer getScor() {
        return scor;
    }

    public void setScor(Integer scor) {
        this.scor = scor;
    }

    public String getSesiuneId() {
        return sesiuneId;
    }

    public void setSesiuneId(String sesiuneId) {
        this.sesiuneId = sesiuneId;
    }
}
