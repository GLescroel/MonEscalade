package glescroel.escalade.mapper;

import glescroel.escalade.dto.LongueurDto;
import glescroel.escalade.dto.SecteurDto;
import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.dto.VoieDto;
import glescroel.escalade.model.Longueur;
import glescroel.escalade.model.Secteur;
import glescroel.escalade.model.Site;
import glescroel.escalade.model.Voie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SecteurMapper {

    SecteurMapper INSTANCE = Mappers.getMapper(SecteurMapper.class);

//    SecteurDto map(Secteur secteur);
//    Secteur map(SecteurDto secteurDto);

    default List<Secteur> dtosToSecteurs(List<SecteurDto> secteursDto) {
        List<Secteur> secteurs = new ArrayList<>();
        for (SecteurDto secteurDto : secteursDto) {
            secteurs.add(this.map(secteurDto));
        }
        return secteurs;
    }
    default List<SecteurDto> secteursToDtos(List<Secteur> secteurs) {
        List<SecteurDto> secteurDtoList = new ArrayList<>();
        for (Secteur secteur : secteurs) {
            secteurDtoList.add(this.map(secteur));
        }
        return secteurDtoList;
    }

    //Child
    @Mappings({
//            @Mapping(target = "secteurs", ignore = true)
    })
    SiteDto map(Site site);

    @Mappings({
//            @Mapping(target = "secteurs", ignore = true)
    })
    Site map(SiteDto siteDto);

    //Parent
    @Mappings({
            @Mapping(target = "secteur.voies", ignore = true),
            @Mapping(target = "secteur", ignore = true)
    })
    VoieDto map(Voie voie);

    @Mappings({
            @Mapping(target = "secteur.voies", ignore = true),
            @Mapping(target = "secteur", ignore = true)
    })
    Voie map(VoieDto voieDto);






    //parent
    @Mappings({
            @Mapping(target = "voie.longueurs", ignore = true),
            @Mapping(target = "voie", ignore = true)
    })
    LongueurDto map(Longueur longueur);

    @Mappings({
            @Mapping(target = "voie.longueurs", ignore = true),
            @Mapping(target = "voie", ignore = true)
    })
    Longueur map(LongueurDto longueurDto);

    //child
    @Mappings({
            @Mapping(target = "voies", ignore = true)
    })
    SecteurDto map(Secteur secteur);

    @Mappings({
            @Mapping(target = "voies", ignore = true)
    })
    Secteur map(SecteurDto secteurDto);

}
