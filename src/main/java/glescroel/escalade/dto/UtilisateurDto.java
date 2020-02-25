package glescroel.escalade.dto;

import glescroel.escalade.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UtilisateurDto {

    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;
    private List<CommentaireDto> commentaires;
    private List<TopoDto> topos;

    public UtilisateurDto() {
        //default constructor vs Lombok
    }

    public void addCommentaire(CommentaireDto commentaire) {
        commentaires.add(commentaire);
    }

    public void removeAllCommentaires() {
        commentaires = new ArrayList<>();
    }
}
