package glescroel.escalade.dto;

import java.util.List;

public class LongueurDto {

    private Integer id;
    private String nom;
    private String cotation;
    private VoieDto voie;
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

    public VoieDto getVoie() {
        return voie;
    }

    public void setVoie(VoieDto voie) {
        this.voie = voie;
    }

    public List<CommentaireDto> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<CommentaireDto> commentaires) {
        this.commentaires = commentaires;
    }
}
