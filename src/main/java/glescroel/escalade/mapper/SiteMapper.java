package glescroel.escalade.mapper;

import glescroel.escalade.dto.SiteDto;
import glescroel.escalade.model.Site;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SiteMapper {
    SiteMapper INSTANCE = Mappers.getMapper(SiteMapper.class);

    SiteDto map(Site site);
    Site map(SiteDto siteDto);

    List<Site> dtosToSites(List<SiteDto> sitesDto);
    List<SiteDto> sitesToDtos(List<Site> sites);
}
