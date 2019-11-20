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
    private LocalisationDto localisation;
    private String description;
    private Date parution;
    private List<SiteDto> sites;
    private UtilisateurDto utilisateur;
    private List<CommentaireDto> commentaires;

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

    public LocalisationDto getLocalisation() {
        return localisation;
    }

    public void setLocalisation(LocalisationDto localisation) {
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

    public List<SiteDto> getSites() {
        return sites;
    }

    public void setSites(List<SiteDto> sites) {
        this.sites = sites;
    }

    public UtilisateurDto getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurDto utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<CommentaireDto> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<CommentaireDto> commentaires) {
        this.commentaires = commentaires;
    }
}
