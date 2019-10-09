package glescroel.escalade.model;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.COMMENTAIRE_COMMENTAIRE_LENGTH, min = 10, max = 1000)
    private String commentaire;

    @OneToOne
    private Topo topo;

    @OneToOne
    private Site site;

    @OneToOne
    private Secteur secteur;

    @OneToOne
    private Voie voie;

    @OneToOne
    private Longueur longueur;

    @OneToOne
    private Utilisateur utilisateur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Topo getTopo() {
        return topo;
    }

    public void setTopo(Topo topo) {
        this.topo = topo;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public Voie getVoie() {
        return voie;
    }

    public void setVoie(Voie voie) {
        this.voie = voie;
    }

    public Longueur getLongueur() {
        return longueur;
    }

    public void setLongueur(Longueur longueur) {
        this.longueur = longueur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
