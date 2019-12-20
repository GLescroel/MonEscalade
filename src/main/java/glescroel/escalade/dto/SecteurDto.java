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
public class SecteurDto {

    private Integer id;
    private String nom;
    private SiteDto site;
    private List<VoieDto> voies;
    private List<CommentaireDto> commentaires;

    public SecteurDto() {
        //default constructor vs lombok
    }
}
