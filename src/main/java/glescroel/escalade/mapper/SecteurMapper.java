package glescroel.escalade.mapper;

import glescroel.escalade.dto.SecteurDto;
import glescroel.escalade.model.Secteur;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = {SiteMapper.class, VoieMapper.class, LongueurMapper.class})
public interface SecteurMapper {

    SecteurMapper INSTANCE = Mappers.getMapper(SecteurMapper.class);
    SiteMapper SITE_MAPPER = Mappers.getMapper(SiteMapper.class);

    @Mappings({
            @Mapping(target = "site", ignore = true),
            @Mapping(target = "site.secteurs", ignore = true)
    })
    SecteurDto map(Secteur secteur);

    @Mappings({
            @Mapping(target = "site", ignore = true),
            @Mapping(target = "site.secteurs", ignore = true)
    })
    Secteur map(SecteurDto secteurDto);

    default List<Secteur> dtosToSecteurs(List<SecteurDto> secteursDto) {
        if (secteursDto == null) {
            return null;
        }
        List<Secteur> secteurs = new ArrayList<>();
        for (SecteurDto secteurDto : secteursDto) {
            secteurs.add(this.map(secteurDto));
        }
        return secteurs;
    }

    default List<SecteurDto> secteursToDtos(List<Secteur> secteurs) {
        if (secteurs == null) {
            return null;
        }
        List<SecteurDto> secteurDtoList = new ArrayList<>();
        for (Secteur secteur : secteurs) {
            secteurDtoList.add(this.map(secteur));
        }
        return secteurDtoList;
    }

    @AfterMapping
    static void addSite(SecteurDto secteurDto, @MappingTarget Secteur secteur) {
        secteur.setSite(SITE_MAPPER.map(secteurDto.getSite()));
    }
}