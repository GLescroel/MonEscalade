package glescroel.escalade.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentaireDto {

    private Integer id;
    private String commentaire;
    private TopoDto topo;
    private SiteDto site;
    private SecteurDto secteur;
    private VoieDto voie;
    private LongueurDto longueur;
    private UtilisateurDto utilisateur;
}
