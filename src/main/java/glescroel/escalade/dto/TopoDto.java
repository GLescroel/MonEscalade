package glescroel.escalade.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TopoDto {

    private Integer id;
    private String nom;
    private LocalisationDto localisation;
    private String description;
    private Date parution;
    private List<SiteDto> sites;
    private UtilisateurDto utilisateur;
    private List<CommentaireDto> commentaires;
}
