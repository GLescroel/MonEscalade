package glescroel.escalade.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SiteDto {

    private Integer id;
    private String nom;
    private LocalisationDto localisation;
    private boolean tag;
    private UtilisateurDto utilisateur;
    private List<SecteurDto> secteurs;
    private List<TopoDto> topos;
    private List<CommentaireDto> commentaires;
}
