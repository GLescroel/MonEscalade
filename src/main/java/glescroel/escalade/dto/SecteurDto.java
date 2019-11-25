package glescroel.escalade.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SecteurDto {

    private Integer id;
    private String nom;
    private SiteDto site;
    private List<VoieDto> voies;
    private List<CommentaireDto> commentaires;
}
