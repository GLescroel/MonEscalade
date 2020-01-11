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
public class LongueurDto {

    private Integer id;
    private String nom;
    private String cotation;
    private VoieDto voie;
    private List<CommentaireDto> commentaires;

    public LongueurDto() {
        //default constructor vs lombok
    }
}
