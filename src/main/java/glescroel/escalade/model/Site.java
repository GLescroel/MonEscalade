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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
public class Site {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Integer id;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.SITE_NOM_LENGTH, max = 50)
    private String nom;

    @OneToOne
    private Localisation localisation;

    @Basic
    private boolean tag;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
    private List<Secteur> secteurs;

    @OneToMany
    private List<Commentaire> commentaires;
}
