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
    private List<SecteurDto> secteurs;
    private List<CommentaireDto> commentaires;
    private String cotationsMin;
    private String cotationsMax;

    public SiteDto() {
        //default constructor (vs Lombok one)
    }

    public void addComment(CommentaireDto commentaire) {
        commentaires.add(commentaire);
    }

    public void addSecteur(SecteurDto secteur) {
        secteurs.add(secteur);
    }

    public void removeCommentaire(CommentaireDto commentaire) {
        int index = 0;
        for (int i = 0 ; i < commentaires.size() ; i++) {
            if(commentaires.get(i).getId() == commentaire.getId()) {
                index = i;
            }
        }
        commentaires.remove(index);
    }
}
