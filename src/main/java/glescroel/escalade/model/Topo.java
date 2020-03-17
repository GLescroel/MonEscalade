package glescroel.escalade.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
public class Topo {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Integer id;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.TOPO_NOM_LENGTH, min = 1, max = 100)
    private String nom;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.TOPO_DESCRIPTION_LENGTH, max = 100)
    private String description;

    @Basic
    @ManyToOne
    private Continent continent;

    @Basic
    @ManyToOne
    private Pays pays;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.TOPO_LIEU_LENGTH, max = 100)
    private String lieu;

    @Basic
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date parution;

    @ManyToOne(optional = false)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur proprietaire;

    @ManyToOne(optional = false)
    private Etat etat;

    @OneToOne
    private Utilisateur emprunteur;
}
