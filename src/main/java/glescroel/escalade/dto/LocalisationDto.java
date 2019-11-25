package glescroel.escalade.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalisationDto {

    private Integer id;
    private PaysDto pays;
    private ContinentDto continent;
    private String region;
    private String departement;
    private String ville;
    private String adresse;
}
