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
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Localisation {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Integer id;

    @OneToOne
    private Site site;

    @ManyToOne
    private Pays pays;

    @ManyToOne
    private Continent continent;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.LOCALISATION_REGION_LENGTH, max = 50)
    private String region;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.LOCALISATION_DEPARTEMENT_LENGTH, max = 50)
    private String departement;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.LOCALISATION_VILLE_LENGTH, max = 50)
    private String ville;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.LOCALISATION_ADRESSE_LENGTH, max = 100)
    private String adresse;
}
