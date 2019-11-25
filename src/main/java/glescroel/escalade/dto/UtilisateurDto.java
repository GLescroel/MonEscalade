package glescroel.escalade.dto;

import glescroel.escalade.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UtilisateurDto {

    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;
    private List<TopoDto> topos;
    private List<CommentaireDto> commentaires;
}
