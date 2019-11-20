package glescroel.escalade.mapper;

import glescroel.escalade.dto.ContinentDto;
import glescroel.escalade.model.Continent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContinentMapper {
    ContinentMapper INSTANCE = Mappers.getMapper(ContinentMapper.class);

    Continent map(ContinentDto continentDto);
    ContinentDto map(Continent continent);

    List<Continent> dtosToContinents(List<ContinentDto> continentDtos);
    List<ContinentDto> continentsToDto(List<Continent> continents);
}
