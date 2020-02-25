package glescroel.escalade.dto;

import glescroel.escalade.model.Etat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopoDto {

    private Integer id;
    private String nom;
    private String description;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date parution;
    private ContinentDto continent;
    private PaysDto pays;
    private String lieu;
    private UtilisateurDto proprietaire;
    private UtilisateurDto emprunteur;
    private List<CommentaireDto> commentaires;
    private Etat etat;
}
