package glescroel.escalade.dto;

import glescroel.escalade.model.Commentaire;
import glescroel.escalade.model.Longueur;
import glescroel.escalade.model.Secteur;
import glescroel.escalade.model.Site;
import glescroel.escalade.model.Topo;
import glescroel.escalade.model.Utilisateur;
import glescroel.escalade.model.Voie;

public class CommentaireDto {

    private Integer id;
    private String commentaire;
    private Topo topo;
    private Site site;
    private Secteur secteur;
    private Voie voie;
    private Longueur longueur;
    private Utilisateur utilisateur;

    public CommentaireDto(Commentaire commentaire) {
        this.id = commentaire.getId();
        this.commentaire = commentaire.getCommentaire();
        this.topo = commentaire.getTopo();
        this.site = commentaire.getSite();
        this.secteur = commentaire.getSecteur();
        this.voie = commentaire.getVoie();
        this.longueur = commentaire.getLongueur();
        this.utilisateur = commentaire.getUtilisateur();
    }

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
