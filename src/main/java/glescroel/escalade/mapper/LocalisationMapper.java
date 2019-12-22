package glescroel.escalade.mapper;

import glescroel.escalade.dto.LocalisationDto;
import glescroel.escalade.model.Localisation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface LocalisationMapper {

    LocalisationMapper INSTANCE = Mappers.getMapper(LocalisationMapper.class);

    Localisation map(LocalisationDto localisationDto);
    LocalisationDto map(Localisation localisation);

    default List<Localisation> dtosToLocalisations(List<LocalisationDto> localisationDtos) {
        List<Localisation> localisations = new ArrayList<>();
        for (LocalisationDto localisationDto : localisationDtos) {
            localisations.add(this.map(localisationDto));
        }
        return localisations;
    }

    default List<LocalisationDto> localisationsToDtos(List<Localisation> localisations) {
        List<LocalisationDto> localisationDtoList = new ArrayList<>();
        for (Localisation localisation : localisations) {
            localisationDtoList.add(this.map(localisation));
        }
        return localisationDtoList;
    }
}
