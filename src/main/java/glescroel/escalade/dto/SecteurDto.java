package glescroel.escalade.dto;

import glescroel.escalade.model.Commentaire;
import glescroel.escalade.model.Secteur;
import glescroel.escalade.model.Site;
import glescroel.escalade.model.Voie;

import java.util.List;

public class SecteurDto {

    private Integer id;
    private String nom;
    private Site site;
    private List<Voie> voies;
    private List<Commentaire> commentaires;

    public SecteurDto(Secteur secteur) {
        this.id = secteur.getId();
        this.nom = secteur.getNom();
        this.site = secteur.getSite();
        this.voies = secteur.getVoies();
        this.commentaires = secteur.getCommentaires();
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

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public List<Voie> getVoies() {
        return voies;
    }

    public void setVoies(List<Voie> voies) {
        this.voies = voies;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
}
