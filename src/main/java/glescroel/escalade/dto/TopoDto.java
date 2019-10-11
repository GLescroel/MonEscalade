package glescroel.escalade.dto;

import glescroel.escalade.model.Commentaire;
import glescroel.escalade.model.Localisation;
import glescroel.escalade.model.Site;
import glescroel.escalade.model.Topo;
import glescroel.escalade.model.Utilisateur;

import java.util.Date;
import java.util.List;

public class TopoDto {

    private Integer id;
    private String nom;
    private Localisation localisation;
    private String description;
    private Date parution;
    private List<Site> sites;
    private Utilisateur utilisateur;
    private List<Commentaire> commentaires;

    public TopoDto(Topo topo) {
        this.id = topo.getId();
        this.nom = topo.getNom();
        this.localisation = topo.getLocalisation();
        this.description = topo.getDescription();
        this.parution = topo.getParution();
        this.sites = topo.getSites();
        this.utilisateur = topo.getUtilisateur();
        this.commentaires = topo.getCommentaires();
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

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getParution() {
        return parution;
    }

    public void setParution(Date parution) {
        this.parution = parution;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
}
