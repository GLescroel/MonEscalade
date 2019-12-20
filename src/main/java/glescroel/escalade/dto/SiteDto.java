package glescroel.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SiteDto {

    private Integer id;
    private String nom;
    private LocalisationDto localisation;
    private boolean tag;
    private UtilisateurDto utilisateur;
    private List<SecteurDto> secteurs;
    private List<TopoDto> topos;
    private List<CommentaireDto> commentaires;
    private String cotationsMin;
    private String cotationsMax;

    public SiteDto() {
        //default constructor (vs Lombok one)
    }

    public void addComment(CommentaireDto commentaire) {
        commentaires.add(commentaire);
    }

}
