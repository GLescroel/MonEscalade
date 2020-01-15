package glescroel.escalade.mapper;

import glescroel.escalade.dto.LongueurDto;
import glescroel.escalade.model.Longueur;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = {SiteMapper.class, SecteurMapper.class, VoieMapper.class})
public interface LongueurMapper {

    LongueurMapper INSTANCE = Mappers.getMapper(LongueurMapper.class);
    VoieMapper VOIE_MAPPER = Mappers.getMapper(VoieMapper.class);

    @Mappings({
            @Mapping(target = "voie.longueurs", ignore = true),
            @Mapping(target = "voie", ignore = true)
    })
    Longueur map(LongueurDto longueurDto);

    @Mappings({
            @Mapping(target = "voie.longueurs", ignore = true),
            @Mapping(target = "voie", ignore = true)
    })
    LongueurDto map(Longueur longueur);

    @AfterMapping
    default void addVoieToDto(Longueur longueur, @MappingTarget LongueurDto longueurDto) {
        longueurDto.setVoie(VOIE_MAPPER.map(longueur.getVoie()));
    }

    @AfterMapping
    default void addVoieToLongueur(LongueurDto longueurDto, @MappingTarget Longueur longueur) {
        longueur.setVoie(VOIE_MAPPER.map(longueurDto.getVoie()));
    }

    default List<Longueur> dtosToLongueurs(List<LongueurDto> longueurDtos) {
        if (longueurDtos == null) {
            return null;
        }
        List<Longueur> longueurs = new ArrayList<>();
        for (LongueurDto longueurDto : longueurDtos) {
            longueurs.add(this.map(longueurDto));
        }
        return longueurs;
    }

    default List<LongueurDto> longueursToDtos(List<Longueur> longueurs) {
        if (longueurs == null) {
            return null;
        }
        List<LongueurDto> longueurDtoList = new ArrayList<>();
        for (Longueur longueur : longueurs) {
            longueurDtoList.add(this.map(longueur));
        }
        return longueurDtoList;
    }
}
