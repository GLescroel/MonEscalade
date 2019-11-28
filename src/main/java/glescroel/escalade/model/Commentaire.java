package glescroel.escalade.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Size(message = ErrorMessages.COMMENTAIRE_COMMENTAIRE_LENGTH, min = 10, max = 1000)
    private String commentaire;

//    @OneToOne
//    private Topo topo;

//    @OneToOne
//    private Site site;

//    @OneToOne
//    private Secteur secteur;

//    @OneToOne
//    private Voie voie;

//    @OneToOne
//    private Longueur longueur;

    @OneToOne
    private Utilisateur utilisateur;
}
