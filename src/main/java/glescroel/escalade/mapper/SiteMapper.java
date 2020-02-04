package glescroel.escalade.mapper;

import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.model.Site;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = {SecteurMapper.class, VoieMapper.class, LongueurMapper.class, CommentaireMapper.class, UtilisateurMapper.class})
public interface SiteMapper {
    SiteMapper INSTANCE = Mappers.getMapper(SiteMapper.class);

    SiteDto map(Site site);
    Site map(SiteDto siteDto);

    default List<Site> dtosToSites(List<SiteDto> sitesDto) {
        if(sitesDto == null) {
            return null;
        }
        List<Site> sites = new ArrayList<>();
        for (SiteDto siteDto : sitesDto) {
            sites.add(this.map(siteDto));
        }
        return sites;
    }
    default List<SiteDto> sitesToDtos(List<Site> sites) {
        if(sites == null) {
            return null;
        }
        List<SiteDto> siteDtoList = new ArrayList<>();
        for (Site site : sites) {
            siteDtoList.add(this.map(site));
        }
        return siteDtoList;
    }
}
