package glescroel.escalade.dto;

import glescroel.escalade.model.Commentaire;
import glescroel.escalade.model.Longueur;
import glescroel.escalade.model.Site;
import glescroel.escalade.model.Voie;

import java.util.List;

public class LongueurDto {

    private Integer id;
    private String nom;
    private String cotation;
    private Voie voie;
    private List<Commentaire> commentaires;

    public LongueurDto(Longueur longueur) {
        this.id = longueur.getId();
        this.nom = longueur.getNom();
        this.cotation = longueur.getCotation();
        this.voie = longueur.getVoie();
        this.commentaires = longueur.getCommentaires();
    }

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

    public String getCotation() {
        return cotation;
    }

    public void setCotation(String cotation) {
        this.cotation = cotation;
    }

    public Voie getVoie() {
        return voie;
    }

    public void setVoie(Voie voie) {
        this.voie = voie;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
}
