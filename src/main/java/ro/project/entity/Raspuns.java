package ro.project.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
public class Raspuns {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn
    private Intrebare intrebare;

    private Integer variantaId;

    private String sesiuneId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVariantaId() {
        return variantaId;
    }

    public void setVariantaId(Integer variantaId) {
        this.variantaId = variantaId;
    }

    public Intrebare getIntrebare() {
        return intrebare;
    }

    public void setIntrebare(Intrebare intrebare) {
        this.intrebare = intrebare;
    }

    public String getSesiuneId() {
        return sesiuneId;
    }

    public void setSesiuneId(String sesiuneId) {
        this.sesiuneId = sesiuneId;
    }


}
