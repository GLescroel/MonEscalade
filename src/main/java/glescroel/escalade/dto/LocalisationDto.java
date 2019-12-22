package glescroel.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LocalisationDto {

    private Integer id;
    private PaysDto pays;
    private ContinentDto continent;
    private SiteDto site;
    private String region;
    private String departement;
    private String ville;
    private String adresse;

    public LocalisationDto() {
        //constructeur par défaut (vs Lombok)
    }
}
