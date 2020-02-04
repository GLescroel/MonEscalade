package glescroel.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CommentaireDto {

    private Integer id;
    private String commentaire;
    private UtilisateurDto utilisateur;

    public CommentaireDto(){
        //constructeur par défaut (vs builder)
    }
}
