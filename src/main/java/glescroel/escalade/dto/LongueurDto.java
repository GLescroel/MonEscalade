package glescroel.escalade.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LongueurDto {

    private Integer id;
    private String nom;
    private String cotation;
    private VoieDto voie;
    private List<CommentaireDto> commentaires;
}
