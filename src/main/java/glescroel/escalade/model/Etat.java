package glescroel.escalade.model;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.Map;

@Entity
public class Etat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.ETAT_NOM_LENGTH, min = 1, max = 20)
    private String etat;

    @OneToMany
    private Map<Topo, Utilisateur> topoUtilisateur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
