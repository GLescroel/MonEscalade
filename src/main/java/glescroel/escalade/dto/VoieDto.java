package glescroel.escalade.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VoieDto {

    private Integer id;
    private String nom;
    private String cotation;
    private boolean equipee;
    private SecteurDto secteur;
    private List<LongueurDto> longueurs;
    private List<CommentaireDto> commentaires;
}
