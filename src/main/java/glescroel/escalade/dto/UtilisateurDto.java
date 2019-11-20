package glescroel.escalade.dto;

import glescroel.escalade.model.Role;

import java.util.List;

public class UtilisateurDto {

    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;
    private List<TopoDto> topos;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
