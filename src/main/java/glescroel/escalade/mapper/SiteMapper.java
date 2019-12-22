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
public interface SiteMapper {
    SiteMapper INSTANCE = Mappers.getMapper(SiteMapper.class);

    SiteDto map(Site site);
    Site map(SiteDto siteDto);

    default List<Site> dtosToSites(List<SiteDto> sitesDto) {
        List<Site> sites = new ArrayList<>();
        for (SiteDto siteDto : sitesDto) {
            sites.add(this.map(siteDto));
        }
        return sites;
    }
    default List<SiteDto> sitesToDtos(List<Site> sites) {
        List<SiteDto> siteDtoList = new ArrayList<>();
        for (Site site : sites) {
            siteDtoList.add(this.map(site));
        }
        return siteDtoList;
    }

    //Parent
    @Mappings({
            @Mapping(target = "site.secteurs", ignore = true),
//            @Mapping(target = "voies", ignore = true)
    })
    SecteurDto map(Secteur secteur);

    @Mappings({
            @Mapping(target = "site.secteurs", ignore = true),
//            @Mapping(target = "voies", ignore = true)
    })
    Secteur map(SecteurDto secteurDto);








    //Parent
    @Mappings({
            @Mapping(target = "secteur.voies", ignore = true),
//            @Mapping(target = "longueurs", ignore = true)
            @Mapping(target = "secteur", ignore = true)
    })
    VoieDto map(Voie voie);

    @Mappings({
            @Mapping(target = "secteur.voies", ignore = true),
//            @Mapping(target = "longueurs", ignore = true)
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




}
