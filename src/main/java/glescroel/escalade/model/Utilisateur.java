package glescroel.escalade.model;

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
    @Size(message = ErrorMessages.UTILISATEUR_PWD_LENGTH, min = 8, max = 15)
    private String password;

    @OneToOne
    private Role role;

    @OneToMany
    private List<Topo> topos;

    @OneToMany
    private List<Commentaire> commentaires;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public List<Topo> getTopos() {
        return topos;
    }

    public void setTopos(List<Topo> topos) {
        this.topos = topos;
    }
}
