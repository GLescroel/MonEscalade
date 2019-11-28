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
//    private TopoDto topo;
//    private SiteDto site;
//    private SecteurDto secteur;
//    private VoieDto voie;
//    private LongueurDto longueur;
    private UtilisateurDto utilisateur;

    public CommentaireDto(){};
}
