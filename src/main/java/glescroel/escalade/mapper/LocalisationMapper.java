package glescroel.escalade.mapper;

import glescroel.escalade.dto.LocalisationDto;
import glescroel.escalade.model.Localisation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LocalisationMapper {

    LocalisationMapper INSTANCE = Mappers.getMapper(LocalisationMapper.class);

    Localisation map(LocalisationDto localisationDto);
    LocalisationDto map(Localisation localisation);

    List<Localisation> dtosToLocalisation(List<LocalisationDto> localisationDtos);
    List<LocalisationDto> localisationsToDtos(List<Localisation> localisations);
}
