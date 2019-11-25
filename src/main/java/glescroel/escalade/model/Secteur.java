package glescroel.escalade.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
public class Secteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.SECTEUR_NOM_LENGTH, min = 1, max = 50)
    private String nom;

    @OneToOne
    private Site site;

    @OneToMany
    private List<Voie> voies;

    @OneToMany
    private List<Commentaire> commentaires;
}
