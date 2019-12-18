package glescroel.escalade.model;

import glescroel.escalade.dto.ContinentDto;
import glescroel.escalade.dto.PaysDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Recherche {

    private String nomSite;
    private String nomContinent;
    private String nomPays;
    private String region;
    private ContinentDto continent;
    private PaysDto pays;

    public Recherche() {
        //constructeur par défaut vs lombok
    }
}
