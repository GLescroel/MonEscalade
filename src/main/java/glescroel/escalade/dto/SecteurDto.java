package glescroel.escalade.dto;

import java.util.List;

public class SecteurDto {

    private Integer id;
    private String nom;
    private SiteDto site;
    private List<VoieDto> voies;
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

    public SiteDto getSite() {
        return site;
    }

    public void setSite(SiteDto site) {
        this.site = site;
    }

    public List<VoieDto> getVoies() {
        return voies;
    }

    public void setVoies(List<VoieDto> voies) {
        this.voies = voies;
    }

    public List<CommentaireDto> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<CommentaireDto> commentaires) {
        this.commentaires = commentaires;
    }
}
