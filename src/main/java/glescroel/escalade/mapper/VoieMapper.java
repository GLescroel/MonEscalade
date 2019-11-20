package glescroel.escalade.mapper;

import glescroel.escalade.dto.VoieDto;
import glescroel.escalade.model.Voie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VoieMapper {

    VoieMapper INSTANCE = Mappers.getMapper(VoieMapper.class);

    Voie map(VoieDto voieDto);
    VoieDto map(Voie voie);

    List<Voie> dtosToVoie(List<VoieDto> voieDtos);
    List<VoieDto> voiesToDtos(List<Voie> voies);

}
