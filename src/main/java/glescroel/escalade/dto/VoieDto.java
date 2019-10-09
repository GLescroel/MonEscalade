package glescroel.escalade.dto;

import glescroel.escalade.model.Commentaire;
import glescroel.escalade.model.Longueur;
import glescroel.escalade.model.Secteur;
import glescroel.escalade.model.Voie;

import java.util.List;

public class VoieDto {

    private Integer id;
    private String nom;
    private String cotation;
    private boolean equipee;
    private Secteur secteur;
    private List<Longueur> longueurs;
    private List<Commentaire> commentaires;

    public VoieDto(Voie voie) {
        this.id = voie.getId();
        this.nom = voie.getNom();
        this.cotation = voie.getCotation();
        this.equipee = voie.isEquipee();
        this.secteur = voie.getSecteur();
        this.longueurs = voie.getLongueurs();
        this.commentaires = voie.getCommentaires();
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

    public boolean isEquipee() {
        return equipee;
    }

    public void setEquipee(boolean equipee) {
        this.equipee = equipee;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public List<Longueur> getLongueurs() {
        return longueurs;
    }

    public void setLongueurs(List<Longueur> longueurs) {
        this.longueurs = longueurs;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
}
