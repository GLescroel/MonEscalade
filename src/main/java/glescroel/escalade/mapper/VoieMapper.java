package glescroel.escalade.mapper;

import glescroel.escalade.dto.VoieDto;
import glescroel.escalade.model.Voie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = {SiteMapper.class, SecteurMapper.class, LongueurMapper.class})
public interface VoieMapper {

    VoieMapper INSTANCE = Mappers.getMapper(VoieMapper.class);

    @Mappings({
            @Mapping(target = "secteur", ignore = true),
            @Mapping(target = "secteur.voies", ignore = true)
    })
    VoieDto map(Voie voie);

    @Mappings({
            @Mapping(target = "secteur", ignore = true),
            @Mapping(target = "secteur.voies", ignore = true)
    })
    Voie map(VoieDto voieDto);

    default List<Voie> dtosToVoies(List<VoieDto> voieDtos) {
        if (voieDtos == null) {
            return null;
        }
        List<Voie> voies = new ArrayList<>();
        for (VoieDto voieDto : voieDtos) {
            voies.add(this.map(voieDto));
        }
        return voies;
    }

    default List<VoieDto> voiesToDtos(List<Voie> voies) {
        if (voies == null) {
            return null;
        }
        List<VoieDto> voieDtoList = new ArrayList<>();
        for (Voie voie : voies) {
            voieDtoList.add(this.map(voie));
        }
        return voieDtoList;
    }
}
