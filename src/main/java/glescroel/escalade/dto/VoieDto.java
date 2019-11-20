package glescroel.escalade.dto;

import java.util.List;

public class VoieDto {

    private Integer id;
    private String nom;
    private String cotation;
    private boolean equipee;
    private SecteurDto secteur;
    private List<LongueurDto> longueurs;
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

    public String getCotation() {
        return cotation;
    }

    public void setCotation(String cotation) {
        this.cotation = cotation;
    }

    public boolean isEquipee() {
        return equipee;
    }

    public void setEquipee(boolean equipee) {
        this.equipee = equipee;
    }

    public SecteurDto getSecteur() {
        return secteur;
    }

    public void setSecteur(SecteurDto secteur) {
        this.secteur = secteur;
    }

    public List<LongueurDto> getLongueurs() {
        return longueurs;
    }

    public void setLongueurs(List<LongueurDto> longueurs) {
        this.longueurs = longueurs;
    }

    public List<CommentaireDto> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<CommentaireDto> commentaires) {
        this.commentaires = commentaires;
    }
}
