package glescroel.escalade.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.SITE_NOM_LENGTH, max = 50)
    private String nom;

    @OneToOne
    private Localisation localisation;

    @Basic
    private boolean tag;

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToMany
    private List<Secteur> secteurs;

    @ManyToMany
    private List<Topo> topos;

    @OneToMany
    private List<Commentaire> commentaires;
}
