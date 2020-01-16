package glescroel.escalade.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
public class Secteur {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Integer id;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.SECTEUR_NOM_LENGTH, min = 1, max = 50)
    private String nom;

    @ManyToOne(optional = false)
    @JoinColumn(name = "site_id")
    private Site site;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "secteur")
    private List<Voie> voies;

    @OneToMany
    private List<Commentaire> commentaires;
}
