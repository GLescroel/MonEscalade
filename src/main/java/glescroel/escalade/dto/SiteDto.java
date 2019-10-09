package glescroel.escalade.dto;

import glescroel.escalade.model.Commentaire;
import glescroel.escalade.model.Secteur;
import glescroel.escalade.model.Site;
import glescroel.escalade.model.Topo;
import glescroel.escalade.model.Utilisateur;

import java.util.List;

public class SiteDto {

    private Integer id;
    private String nom;
    private String localisation;
    private boolean tag;
    private Utilisateur utilisateur;
    private List<Secteur> secteurs;
    private List<Topo> topos;
    private List<Commentaire> commentaires;


    public SiteDto(Site site){
        this.nom = site.getNom();
        this.id = site.getId();
        this.localisation = site.getLocalisation();
        this.tag = site.isTag();
        this.utilisateur = site.getUtilisateur();
        this.secteurs = site.getSecteurs();
        this.topos = site.getTopos();
        this.commentaires = site.getCommentaires();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public boolean isTag() {
        return tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Secteur> getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(List<Secteur> secteurs) {
        this.secteurs = secteurs;
    }

    public List<Topo> getTopos() {
        return topos;
    }

    public void setTopos(List<Topo> topos) {
        this.topos = topos;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
}
