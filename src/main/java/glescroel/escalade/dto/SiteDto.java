package glescroel.escalade.dto;

import java.util.List;

public class SiteDto {

    private Integer id;
    private String nom;
    private LocalisationDto localisation;
    private boolean tag;
    private UtilisateurDto utilisateur;
    private List<SecteurDto> secteurs;
    private List<TopoDto> topos;
    private List<CommentaireDto> commentaires;

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

    public LocalisationDto getLocalisation() {
        return localisation;
    }

    public void setLocalisation(LocalisationDto localisation) {
        this.localisation = localisation;
    }

    public boolean isTag() {
        return tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    public UtilisateurDto getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurDto utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<SecteurDto> getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(List<SecteurDto> secteurs) {
        this.secteurs = secteurs;
    }

    public List<TopoDto> getTopos() {
        return topos;
    }

    public void setTopos(List<TopoDto> topos) {
        this.topos = topos;
    }

    public List<CommentaireDto> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<CommentaireDto> commentaires) {
        this.commentaires = commentaires;
    }
}
