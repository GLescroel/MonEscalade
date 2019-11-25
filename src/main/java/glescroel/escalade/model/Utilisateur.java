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
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.UTILISATEUR_NOM_LENGTH, min = 1, max = 30)
    private String nom;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.UTILISATEUR_PRENOM_LENGTH, min = 1, max = 30)
    private String prenom;

    @Basic
    @Email
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.UTILISATEUR_EMAIL_LENGTH, min = 5, max = 50)
    private String email;

    @Basic
    @ColumnTransformer()
    @Size(message = ErrorMessages.UTILISATEUR_MDP_LENGTH, min = 8, max = 15)
    private String password;

    @OneToOne
    private Role role;

    @OneToMany
    private List<Topo> topos;

    @OneToMany
    private List<Commentaire> commentaires;
}
